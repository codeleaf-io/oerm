package io.codeleaf.dal.tasks;

import io.codeleaf.dal.SearchPage;

public interface PageSearchTask<D, F, V, H, O extends SearchPage<H>>
        extends
        SelectTask<D, F, V, O>,
        ProjectTask<D, F, O>,
        PaginateTask<D, F, H, O> {

    interface Builder<
            B extends Builder<B, T, D, F, V, H, O>,
            T extends PageSearchTask<D, F, V, H, O>,
            D,
            F,
            V,
            H,
            O extends SearchPage<H>>
            extends
            SelectTask.Builder<B, T, D, F, V, O>,
            ProjectTask.Builder<B, T, D, F, O>,
            PaginateTask.Builder<B, T, D, F, H, O> {
    }
}
