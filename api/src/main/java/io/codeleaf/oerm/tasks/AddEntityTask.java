package io.codeleaf.oerm.tasks;

public interface AddEntityTask<E, K, D>
        extends
        WriteEntityTask<E, D, K>,
        AddTask<K, D> {

    interface Builder<
            B extends Builder<B, T, E, K, D>,
            T extends AddEntityTask<E, K, D>,
            E,
            K,
            D>
            extends
            WriteEntityTask.Builder<B, T, E, D, K>,
            AddTask.Builder<B, T, K, D> {
    }
}
