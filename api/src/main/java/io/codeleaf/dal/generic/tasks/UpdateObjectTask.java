package io.codeleaf.dal.generic.tasks;

public interface UpdateObjectTask<E, D, F, V, O>
        extends
        WriteObjectTask<E, D, O>,
        SelectTask<D, F, V, O>,
        UpdateTask<D, O> {

    interface Builder<
            B extends Builder<B, T, E, D, F, V, O>,
            T extends UpdateObjectTask<E, D, F, V, O>,
            E,
            D,
            F,
            V,
            O>
            extends
            WriteObjectTask.Builder<B, T, E, D, O>,
            SelectTask.Builder<B, T, D, F, V, O>,
            UpdateTask.Builder<B, T, D, O> {
    }
}
