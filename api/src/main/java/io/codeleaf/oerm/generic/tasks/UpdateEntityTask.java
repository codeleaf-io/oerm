package io.codeleaf.oerm.generic.tasks;

public interface UpdateEntityTask<E, D, F, V, O>
        extends
        WriteEntityTask<E, D, O>,
        SelectTask<D, F, V, O>,
        UpdateTask<D, O> {

    interface Builder<
            B extends Builder<B, T, E, D, F, V, O>,
            T extends UpdateEntityTask<E, D, F, V, O>,
            E,
            D,
            F,
            V,
            O>
            extends
            WriteEntityTask.Builder<B, T, E, D, O>,
            SelectTask.Builder<B, T, D, F, V, O>,
            UpdateTask.Builder<B, T, D, O> {
    }
}
