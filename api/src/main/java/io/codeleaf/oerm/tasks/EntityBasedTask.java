package io.codeleaf.oerm.tasks;

public interface EntityBasedTask<E, K, D, F, V, S, O> extends DatabaseTask<E, K, D, F, V, S, O> {

    E getEntity();

    interface Builder<
            B extends Builder<B, T, E, K, D, F, V, S, O>,
            T extends EntityBasedTask<E, K, D, F, V, S, O>,
            E, K, D, F, V, S, O>
            extends DatabaseTask.Builder<B, T, E, K, D, F, V, S, O> {

        B withEntity(E entity);
    }
}
