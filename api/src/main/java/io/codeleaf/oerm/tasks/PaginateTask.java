package io.codeleaf.oerm.tasks;

import io.codeleaf.oerm.SearchPage;

public interface PaginateTask<E, K, D, F, V, S, O extends SearchPage<E>> extends ListTask<E, K, D, F, V, S, O> {

    long getOffset();

    int getLimit();

    interface Builder<
            B extends Builder<B, T, E, K, D, F, V, S, O>,
            T extends PaginateTask<E, K, D, F, V, S, O>,
            E, K, D, F, V, S, O extends SearchPage<E>>
            extends ListTask.Builder<B, T, E, K, D, F, V, S, O> {

        B withOffset(long offset);

        B withLimit(int limit);
    }
}
