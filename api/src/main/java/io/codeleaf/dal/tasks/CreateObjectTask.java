package io.codeleaf.dal.tasks;

public interface CreateObjectTask<E, K, D>
        extends
        WriteObjectTask<E, D, K>,
        CreateTask<K, D> {

    interface Builder<
            B extends Builder<B, T, E, K, D>,
            T extends CreateObjectTask<E, K, D>,
            E,
            K,
            D>
            extends
            WriteObjectTask.Builder<B, T, E, D, K>,
            CreateTask.Builder<B, T, K, D> {
    }
}
