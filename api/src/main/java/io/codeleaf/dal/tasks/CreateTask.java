package io.codeleaf.dal.tasks;

public interface CreateTask<K, D> extends WriteTask<D, K> {

    Class<K> getObjectIdType();

    default Class<K> getOutputType() {
        return getObjectIdType();
    }

    interface Builder<
            B extends Builder<B, T, K, D>,
            T extends CreateTask<K, D>,
            K,
            D>
            extends WriteTask.Builder<B, T, D, K> {

        B withObjectIdType(Class<K> objectIdType);
    }
}
