package io.codeleaf.oerm.generic.tasks;

import io.codeleaf.oerm.Count;

public interface CountTask<D, F, V, O extends Count>
        extends
        SelectTask<D, F, V, O>,
        ReadTask<D, O> {

    interface Builder<
            B extends Builder<B, T, D, F, V, O>,
            T extends CountTask<D, F, V, O>,
            D,
            F,
            V,
            O extends Count>
            extends
            SelectTask.Builder<B, T, D, F, V, O>,
            ReadTask.Builder<B, T, D, O> {
    }
}
