package io.codeleaf.oerm.tasks;

public interface UpdateEntityTask<E, D, O>
        extends
        WriteEntityTask<E, D, O>,
        UpdateTask<D, O> {

    interface Builder<
            B extends Builder<B, T, E, D, O>,
            T extends UpdateEntityTask<E, D, O>,
            E,
            D,
            O>
            extends
            WriteEntityTask.Builder<B, T, E, D, O>,
            UpdateTask.Builder<B, T, D, O> {
    }
}
