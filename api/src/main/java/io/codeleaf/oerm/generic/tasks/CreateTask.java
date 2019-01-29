package io.codeleaf.oerm.generic.tasks;

public interface CreateTask<K, D> extends WriteTask<D, K> {

    Class<K> getEntityIdType();

    default Class<K> getOutputType() {
        return getEntityIdType();
    }

    interface Builder<
            B extends Builder<B, T, K, D>,
            T extends CreateTask<K, D>,
            K,
            D>
            extends WriteTask.Builder<B, T, D, K> {

        B withEntityIdType(Class<K> entityIdType);
    }
}
