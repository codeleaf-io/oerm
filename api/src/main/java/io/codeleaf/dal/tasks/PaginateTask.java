package io.codeleaf.dal.tasks;

import io.codeleaf.dal.SearchPage;

public interface PaginateTask<D, F, H, O extends SearchPage<H>>
        extends SearchTask<D, F, H, O> {

    long getOffset();

    int getLimit();

    interface Builder<
            B extends Builder<B, T, D, F, H, O>,
            T extends PaginateTask<D, F, H, O>,
            D,
            F,
            H,
            O extends SearchPage<H>>
            extends SearchTask.Builder<B, T, D, F, H, O> {

        B withOffset(long offset);

        B withLimit(int limit);
    }
}
