package io.codeleaf.oerm.tasks;

import io.codeleaf.oerm.SearchPageAndCount;

public interface PageSearchAndCountTask<D, F, V, H, O extends SearchPageAndCount<H>>
        extends
        PageSearchTask<D, F, V, H, O>,
        CountTask<D, F, V, O> {

    interface Builder<
            B extends Builder<B, T, D, F, V, H, O>,
            T extends PageSearchAndCountTask<D, F, V, H, O>,
            D,
            F,
            V,
            H,
            O extends SearchPageAndCount<H>>
            extends
            PageSearchTask.Builder<B, T, D, F, V, H, O>,
            CountTask.Builder<B, T, D, F, V, O> {
    }
}
