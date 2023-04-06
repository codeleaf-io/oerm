package io.codeleaf.oerm.generic.delegating;

import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.task.Task;
import io.codeleaf.modeling.task.TaskHandlingException;
import io.codeleaf.oerm.Count;
import io.codeleaf.oerm.SearchPage;
import io.codeleaf.oerm.generic.DatabaseTaskHandler;
import io.codeleaf.oerm.generic.RepositoryTypes;
import io.codeleaf.oerm.generic.tasks.impl.CountTaskImpl;
import io.codeleaf.oerm.generic.tasks.impl.PageSearchAndCountTaskImpl;
import io.codeleaf.oerm.generic.tasks.impl.PageSearchTaskImpl;
import io.codeleaf.oerm.impl.DefaultSearchPage;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Supplier;

public final class DelegatingPageSearchAndCountHandler<E, K, D, F, V, S> implements DatabaseTaskHandler<E, K, D, F, V, S> {

    private final Supplier<DatabaseTaskHandler<E, K, D, F, V, S>> provider;

    public DelegatingPageSearchAndCountHandler(Supplier<DatabaseTaskHandler<E, K, D, F, V, S>> provider) {
        this.provider = provider;
    }

    @Override
    public RepositoryTypes<E, K, D, F, V, S> getGenericTypes() {
        return provider.get().getGenericTypes();
    }

    @Override
    public <T extends Task<?>> boolean supportsTaskType(Class<T> taskClass) {
        return Objects.equals(taskClass, PageSearchAndCountTaskImpl.class) &&
                (supportsCursorSearchTask() && supportsCountTask());
    }

    private boolean supportsCursorSearchTask() {
        return provider.get().supportsTaskType(PageSearchTaskImpl.class);
    }

    private boolean supportsCountTask() {
        return provider.get().supportsTaskType(CountTaskImpl.class);
    }

    private static class Result<H> extends DefaultSearchPage<H> implements Count {

        private final Count count;

        public static <H> Result<H> create(SearchPage<H> searchPage, Count count) {
            Result<H> result = new Result<>(count);
            result.addAll(searchPage);
            return result;
        }

        private Result(Count count) {
            this.count = count;
        }

        @Override
        public long getCount() {
            return count.getCount();
        }
    }

    @Override
    public <O> O handleTask(Task<O> task) throws TaskHandlingException {
        try {
            PageSearchAndCountTaskImpl<D, F, V, ?> cursorSearchAndCountTask = Types.cast(task);
            SearchPage<?> searchPage = doPageSearch(cursorSearchAndCountTask);
            Count count = doCount(cursorSearchAndCountTask);
            return task.getOutputType().cast(Result.create(searchPage, count));
        } catch (RuntimeException | IOException | TaskHandlingException cause) {
            throw new TaskHandlingException(task, "Failed to delegate task: " + cause, cause);
        }
    }

    private SearchPage<?> doPageSearch(PageSearchAndCountTaskImpl<D, F, V, ?> task) throws IOException, TaskHandlingException {
        PageSearchTaskImpl<D, F, V, E> searchTask = provider.get()
                .getDataTaskBuilders(task.getDataType())
                .pageSearch()
                .withSelection(task.getSelection())
                .withOffset(task.getOffset())
                .withLimit(task.getLimit())
                .withProjections(task.getProjection())
                .withOrder(task.getOrder())
                .build();
        return provider.get().handleTask(searchTask);
    }

    private Count doCount(PageSearchAndCountTaskImpl<D, F, V, ?> task) throws IOException, TaskHandlingException {
        CountTaskImpl<D, F, V> countTask = provider.get()
                .getDataTaskBuilders(task.getDataType())
                .count()
                .withSelection(task.getSelection())
                .build();
        return provider.get().handleTask(countTask);
    }

}
