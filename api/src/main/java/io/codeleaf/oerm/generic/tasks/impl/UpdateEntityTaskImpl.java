package io.codeleaf.oerm.generic.tasks.impl;

import io.codeleaf.common.utils.Types;
import io.codeleaf.oerm.generic.tasks.UpdateEntityTask;

import java.util.Objects;

public final class UpdateEntityTaskImpl<E, D> implements UpdateEntityTask<E, D, Void> {

    private final Class<E> entityType;
    private final E entity;
    private final D dataType;

    private UpdateEntityTaskImpl(Class<E> entityType, E entity, D dataType) {
        this.entityType = entityType;
        this.entity = entity;
        this.dataType = dataType;
    }

    @Override
    public Class<E> getEntityType() {
        return entityType;
    }

    @Override
    public E getEntity() {
        return entity;
    }

    @Override
    public D getDataType() {
        return dataType;
    }

    @Override
    public Class<Void> getOutputType() {
        return null;
    }

    public static final class Builder<E, D> implements UpdateEntityTask.Builder<Builder<E, D>, UpdateEntityTaskImpl<E, D>, E, D, Void> {

        private Class<E> entityType;
        private E entity;
        private D dataType;

        public Builder() {
        }

        public Builder(Class<E> entityType, D dataType) {
            this.entityType = entityType;
            this.dataType = dataType;
        }

        @Override
        public Builder<E, D> withEntityType(Class<E> entityType) {
            Objects.requireNonNull(entityType);
            this.entityType = entityType;
            return this;
        }

        @Override
        public Builder<E, D> withEntity(E entity) {
            Objects.requireNonNull(entity);
            this.entity = entity;
            return this;
        }

        @Override
        public Builder<E, D> withDataType(D dataType) {
            Objects.requireNonNull(dataType);
            this.dataType = dataType;
            return this;
        }

        protected void validate() {
            if (entity == null) {
                throw new IllegalStateException("Entity must be set!");
            }
            if (entityType != null && !entityType.isAssignableFrom(entity.getClass())) {
                throw new IllegalStateException("Entity must have the specified entity type!");
            }
            if (dataType == null) {
                throw new IllegalStateException("Data type must be set!");
            }
        }

        @Override
        public UpdateEntityTaskImpl<E, D> build() {
            validate();
            return new UpdateEntityTaskImpl<>(
                    entityType == null ? Types.cast(entity.getClass()) : entityType,
                    entity,
                    dataType);
        }
    }
}
