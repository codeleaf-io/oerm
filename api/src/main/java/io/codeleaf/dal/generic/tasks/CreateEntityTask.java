package io.codeleaf.dal.generic.tasks;

public interface CreateEntityTask<E, K, D>
        extends
        WriteEntityTask<E, D, K>,
        CreateTask<K, D> {

    interface Builder<
            B extends Builder<B, T, E, K, D>,
            T extends CreateEntityTask<E, K, D>,
            E,
            K,
            D>
            extends
            WriteEntityTask.Builder<B, T, E, D, K>,
            CreateTask.Builder<B, T, K, D> {
    }
}
