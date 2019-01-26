package io.codeleaf.dal.tasks;

public interface DeleteTask<D, F, V, O>
        extends
        SelectTask<D, F, V, O>,
        WriteTask<D, O> {

    interface Builder<
            B extends Builder<B, T, D, F, V, O>,
            T extends DeleteTask<D, F, V, O>,
            D,
            F,
            V,
            O>
            extends
            SelectTask.Builder<B, T, D, F, V, O>,
            WriteTask.Builder<B, T, D, O> {
    }
}
