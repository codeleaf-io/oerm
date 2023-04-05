package io.codeleaf.oerm.generic.tasks;

public interface CreateEntityTask<K, D, F, V>
        extends
        WriteFieldsTask<D, F, V, K>,
        AddTask<K, D> {

    interface Builder<
            B extends Builder<B, T, K, D, F, V>,
            T extends CreateEntityTask<K, D, F, V>,
            K,
            D,
            F,
            V>
            extends
            WriteFieldsTask.Builder<B, T, D, F, V, K>,
            AddTask.Builder<B, T, K, D> {
    }
}
