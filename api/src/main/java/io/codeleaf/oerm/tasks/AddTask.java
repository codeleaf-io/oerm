package io.codeleaf.oerm.tasks;

public interface AddTask<K, D> extends WriteTask<D, K> {

    Class<K> getEntityIdType();

    interface Builder<
            B extends Builder<B, T, K, D>,
            T extends AddTask<K, D>,
            K,
            D>
            extends WriteTask.Builder<B, T, D, K> {
    }
}
