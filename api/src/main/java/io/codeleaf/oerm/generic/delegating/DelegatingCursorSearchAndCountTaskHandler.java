package io.codeleaf.oerm.generic.delegating;

import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.task.Task;
import io.codeleaf.modeling.task.TaskHandlingException;
import io.codeleaf.oerm.Count;
import io.codeleaf.oerm.SearchCursor;
import io.codeleaf.oerm.SearchCursorAndCount;
import io.codeleaf.oerm.generic.DatabaseTaskHandler;
import io.codeleaf.oerm.generic.RepositoryTypes;
import io.codeleaf.oerm.generic.tasks.impl.CountTaskImpl;
import io.codeleaf.oerm.generic.tasks.impl.CursorSearchAndCountTaskImpl;
import io.codeleaf.oerm.generic.tasks.impl.CursorSearchTaskImpl;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Supplier;

public final class DelegatingCursorSearchAndCountTaskHandler<E, K, D, F, V, S> implements DatabaseTaskHandler<E, K, D, F, V, S> {

    private final Supplier<DatabaseTaskHandler<E, K, D, F, V, S>> provider;

    public DelegatingCursorSearchAndCountTaskHandler(Supplier<DatabaseTaskHandler<E, K, D, F, V, S>> provider) {
        this.provider = provider;
    }

    @Override
    public RepositoryTypes<E, K, D, F, V, S> getGenericTypes() {
        return provider.get().getGenericTypes();
    }

    @Override
    public <T extends Task<?>> boolean supportsTaskType(Class<T> taskClass) {
        return Objects.equals(taskClass, CursorSearchAndCountTaskImpl.class) &&
                (supportsCursorSearchTask() && supportsCountTask());
    }

    private boolean supportsCursorSearchTask() {
        return provider.get().supportsTaskType(CursorSearchTaskImpl.class);
    }

    private boolean supportsCountTask() {
        return provider.get().supportsTaskType(CountTaskImpl.class);
    }

    @Override
    public <O> O handleTask(Task<O> task) throws TaskHandlingException {
        try {
            CursorSearchAndCountTaskImpl<D, F, V, ?> cursorSearchAndCountTask = Types.cast(task);
            Count count = doCount(cursorSearchAndCountTask);
            SearchCursor<?> searchCursor = doCursorSearch(cursorSearchAndCountTask);
            return task.getOutputType().cast(new SearchCursorAndCount<>() {
                @Override
                public boolean hasNext() {
                    return searchCursor.hasNext();
                }

                @Override
                public Object next() {
                    return searchCursor.next();
                }

                @Override
                public void close() throws IOException {
                    searchCursor.close();
                }

                @Override
                public long getCount() {
                    return count.getCount();
                }
            });
        } catch (RuntimeException | IOException | TaskHandlingException cause) {
            throw new TaskHandlingException(task, "Failed to delegate task: " + cause, cause);
        }
    }

    private SearchCursor<?> doCursorSearch(CursorSearchAndCountTaskImpl<D, F, V, ?> task) throws IOException, TaskHandlingException {
        CursorSearchTaskImpl<D, F, V, E> searchTask = provider.get()
                .getDataTaskBuilders(task.getDataType())
                .cursorSearch()
                .withSelection(task.getSelection())
                .withBufferSize(task.getBufferSize())
                .withProjections(task.getProjection())
                .withOrder(task.getOrder())
                .build();
        return provider.get().handleTask(searchTask);
    }

    private Count doCount(CursorSearchAndCountTaskImpl<D, F, V, ?> task) throws IOException, TaskHandlingException {
        CountTaskImpl<D, F, V> countTask = provider.get()
                .getDataTaskBuilders(task.getDataType())
                .count()
                .withSelection(task.getSelection())
                .build();
        return provider.get().handleTask(countTask);
    }

}
