package io.codeleaf.oerm.generic.delegating;

import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.task.Task;
import io.codeleaf.modeling.task.TaskHandlingException;
import io.codeleaf.oerm.Count;
import io.codeleaf.oerm.SearchCursorAndCount;
import io.codeleaf.oerm.SearchPageAndCount;
import io.codeleaf.oerm.generic.DatabaseTaskHandler;
import io.codeleaf.oerm.generic.RepositoryTypes;
import io.codeleaf.oerm.generic.tasks.CountTask;
import io.codeleaf.oerm.generic.tasks.impl.CountTaskImpl;
import io.codeleaf.oerm.generic.tasks.impl.CursorSearchAndCountTaskImpl;
import io.codeleaf.oerm.generic.tasks.impl.PageSearchAndCountTaskImpl;
import io.codeleaf.oerm.impl.DefaultCount;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Supplier;

public final class DelegatingCountTaskHandler<E, K, D, F, V, S> implements DatabaseTaskHandler<E, K, D, F, V, S> {

    private final Supplier<DatabaseTaskHandler<E, K, D, F, V, S>> provider;

    public DelegatingCountTaskHandler(Supplier<DatabaseTaskHandler<E, K, D, F, V, S>> provider) {
        this.provider = provider;
    }

    @Override
    public RepositoryTypes<E, K, D, F, V, S> getGenericTypes() {
        return provider.get().getGenericTypes();
    }

    @Override
    public <T extends Task<?>> boolean supportsTaskType(Class<T> taskClass) {
        return Objects.equals(taskClass, CountTaskImpl.class) &&
                (supportsPageSearchAndCountTask() || supportsCursorSearchAndCountTask());
    }

    private boolean supportsPageSearchAndCountTask() {
        return provider.get().supportsTaskType(PageSearchAndCountTaskImpl.class);
    }

    private boolean supportsCursorSearchAndCountTask() {
        return provider.get().supportsTaskType(CursorSearchAndCountTaskImpl.class);
    }

    @Override
    public <O> O handleTask(Task<O> task) throws TaskHandlingException {
        try {
            CountTaskImpl<D, F, V> countTask = Types.cast(task);
            Count count;
            if (supportsPageSearchAndCountTask()) {
                count = doPageSearchAndCount(countTask);
            } else if (supportsCursorSearchAndCountTask()) {
                count = doCursorSearchAndCount(countTask);
            } else {
                throw new IllegalStateException();
            }
            return task.getOutputType().cast(count);
        } catch (RuntimeException | IOException | TaskHandlingException cause) {
            throw new TaskHandlingException(task, "Failed to delegate task: " + cause, cause);
        }
    }

    private Count doPageSearchAndCount(CountTask<D, F, V, ?> countTask) throws IOException, TaskHandlingException {
        PageSearchAndCountTaskImpl<D, F, V, E> searchTask = provider.get()
                .getDataTaskBuilders(countTask.getDataType())
                .pageSearchAndCount()
                .withSelection(countTask.getSelection())
                .withOffset(0)
                .withLimit(1)
                .build();
        SearchPageAndCount<E> search = provider.get().handleTask(searchTask);
        return new DefaultCount(search.getCount());
    }

    private Count doCursorSearchAndCount(CountTask<D, F, V, ?> countTask) throws IOException, TaskHandlingException {
        CursorSearchAndCountTaskImpl<D, F, V, E> searchTask = provider.get()
                .getDataTaskBuilders(countTask.getDataType())
                .cursorSearchAndCount()
                .withSelection(countTask.getSelection())
                .withBufferSize(1)
                .build();
        try (SearchCursorAndCount<E> search = provider.get().handleTask(searchTask)) {
            return new DefaultCount(search.getCount());
        }
    }

}
