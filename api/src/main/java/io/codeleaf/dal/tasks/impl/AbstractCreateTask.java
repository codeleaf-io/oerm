package io.codeleaf.dal.tasks.impl;

import io.codeleaf.dal.tasks.CreateTask;

import java.util.Objects;

public abstract class AbstractCreateTask<K, D> implements CreateTask<K, D> {

    private final Class<K> objectIdType;
    private final D dataType;

    protected AbstractCreateTask(Class<K> objectIdType, D dataType) {
        this.objectIdType = objectIdType;
        this.dataType = dataType;
    }

    @Override
    public D getDataType() {
        return dataType;
    }

    @Override
    public Class<K> getObjectIdType() {
        return objectIdType;
    }

    @SuppressWarnings("unchecked")
    public static abstract class Builder<
            B extends Builder<B, T, K, D>,
            T extends AbstractCreateTask<K, D>,
            K,
            D
            > implements CreateTask.Builder<B, T, K, D> {

        protected Class<K> objectIdType;
        protected D dataType;

        public Builder() {
        }

        public Builder(Class<K> objectIdType, D dataType) {
            this.objectIdType = objectIdType;
            this.dataType = dataType;
        }

        @Override
        public B withDataType(D dataType) {
            Objects.requireNonNull(dataType);
            this.dataType = dataType;
            return (B) this;
        }

        @Override
        public B withObjectIdType(Class<K> objectIdType) {
            Objects.requireNonNull(objectIdType);
            this.objectIdType = objectIdType;
            return (B) this;
        }

        protected void validate() {
            if (dataType == null || objectIdType == null) {
                throw new IllegalStateException();
            }
        }
    }
}
