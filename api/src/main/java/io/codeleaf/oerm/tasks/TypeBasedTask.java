package io.codeleaf.oerm.tasks;

public interface TypeBasedTask<E, K, D, F, V, S, O> extends DatabaseTask<E, K, D, F, V, S, O> {

    D getDataType();

    interface Builder<
            B extends Builder<B, T, E, K, D, F, V, S, O>,
            T extends TypeBasedTask<E, K, D, F, V, S, O>,
            E, K, D, F, V, S, O>
            extends DatabaseTask.Builder<B, T, E, K, D, F, V, S, O> {

        B withDataType(D dataType);
    }
}
