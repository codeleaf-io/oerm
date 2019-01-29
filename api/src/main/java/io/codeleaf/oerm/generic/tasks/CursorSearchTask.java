package io.codeleaf.oerm.generic.tasks;

import io.codeleaf.oerm.SearchCursor;

public interface CursorSearchTask<D, F, V, H, O extends SearchCursor<H>>
        extends
        SelectTask<D, F, V, O>,
        ProjectTask<D, F, O>,
        IterateTask<D, F, H, O> {

    interface Builder<
            B extends Builder<B, T, D, F, V, H, O>,
            T extends CursorSearchTask<D, F, V, H, O>,
            D,
            F,
            V,
            H,
            O extends SearchCursor<H>>
            extends
            SelectTask.Builder<B, T, D, F, V, O>,
            ProjectTask.Builder<B, T, D, F, O>,
            IterateTask.Builder<B, T, D, F, H, O> {
    }
}
