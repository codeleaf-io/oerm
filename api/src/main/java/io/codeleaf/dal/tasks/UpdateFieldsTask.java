package io.codeleaf.dal.tasks;

public interface UpdateFieldsTask<D, F, V, O>
        extends
        WriteFieldsTask<D, F, V, O>,
        SelectTask<D, F, V, O>,
        UpdateTask<D, O> {

    interface Builder<
            B extends Builder<B, T, D, F, V, O>,
            T extends UpdateFieldsTask<D, F, V, O>,
            D,
            F,
            V,
            O>
            extends
            WriteFieldsTask.Builder<B, T, D, F, V, O>,
            SelectTask.Builder<B, T, D, F, V, O>,
            UpdateTask.Builder<B, T, D, O> {
    }
}
