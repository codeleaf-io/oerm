package io.codeleaf.dal.tasks;

public interface CreateFieldsTask<K, D, F, V>
        extends
        WriteFieldsTask<D, F, V, K>,
        CreateTask<K, D> {

    interface Builder<
            B extends Builder<B, T, K, D, F, V>,
            T extends CreateFieldsTask<K, D, F, V>,
            K,
            D,
            F,
            V>
            extends
            WriteFieldsTask.Builder<B, T, D, F, V, K>,
            CreateTask.Builder<B, T, K, D> {
    }
}
