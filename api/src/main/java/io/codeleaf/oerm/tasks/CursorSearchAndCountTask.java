package io.codeleaf.oerm.tasks;

import io.codeleaf.oerm.SearchCursorAndCount;

public interface CursorSearchAndCountTask<D, F, V, H, O extends SearchCursorAndCount<H>>
        extends
        CursorSearchTask<D, F, V, H, O>,
        CountTask<D, F, V, O> {

    interface Builder<
            B extends Builder<B, T, D, F, V, H, O>,
            T extends CursorSearchAndCountTask<D, F, V, H, O>,
            D,
            F,
            V,
            H,
            O extends SearchCursorAndCount<H>>
            extends
            CursorSearchTask.Builder<B, T, D, F, V, H, O>,
            CountTask.Builder<B, T, D, F, V, O> {
    }
}
