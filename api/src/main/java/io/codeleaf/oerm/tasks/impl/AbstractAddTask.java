package io.codeleaf.oerm.tasks.impl;

import io.codeleaf.oerm.tasks.AddTask;

public abstract class AbstractAddTask<K, D> extends AbstractDataTask<D, K> implements AddTask<K, D> {

    private final Class<K> entityIdType;

    protected AbstractAddTask(Class<K> entityIdType, D dataType) {
        super(dataType, entityIdType);
        this.entityIdType = entityIdType;
    }

    @Override
    public Class<K> getEntityIdType() {
        return entityIdType;
    }

    public static abstract class Builder<
            B extends Builder<B, T, K, D>,
            T extends AbstractAddTask<K, D>,
            K,
            D
            > extends AbstractDataTask.Builder<B, T, D, K>
            implements AddTask.Builder<B, T, K, D> {

        protected final Class<K> entityIdType;

        public Builder(Class<K> entityIdType, D dataType) {
            super(dataType);
            this.entityIdType = entityIdType;
        }

        @Override
        protected void validate() {
            super.validate();
            if (dataType == null || entityIdType == null) {
                throw new IllegalStateException();
            }
        }
    }
}
