package io.codeleaf.dal.generic.tasks.impl;

import io.codeleaf.common.utils.Types;
import io.codeleaf.dal.generic.tasks.CreateTask;

import java.util.Objects;

public abstract class AbstractCreateTask<K, D> implements CreateTask<K, D> {

    private final Class<K> entityIdType;
    private final D dataType;

    protected AbstractCreateTask(Class<K> entityIdType, D dataType) {
        this.entityIdType = entityIdType;
        this.dataType = dataType;
    }

    @Override
    public D getDataType() {
        return dataType;
    }

    @Override
    public Class<K> getEntityIdType() {
        return entityIdType;
    }

    public static abstract class Builder<
            B extends Builder<B, T, K, D>,
            T extends AbstractCreateTask<K, D>,
            K,
            D
            > implements CreateTask.Builder<B, T, K, D> {

        protected Class<K> entityIdType;
        protected D dataType;

        public Builder() {
        }

        public Builder(Class<K> entityIdType, D dataType) {
            this.entityIdType = entityIdType;
            this.dataType = dataType;
        }

        @Override
        public B withDataType(D dataType) {
            Objects.requireNonNull(dataType);
            this.dataType = dataType;
            return Types.cast(this);
        }

        @Override
        public B withEntityIdType(Class<K> entityIdType) {
            Objects.requireNonNull(entityIdType);
            this.entityIdType = entityIdType;
            return Types.cast(this);
        }

        protected void validate() {
            if (dataType == null || entityIdType == null) {
                throw new IllegalStateException();
            }
        }
    }
}
