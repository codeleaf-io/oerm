package io.codeleaf.oerm.tasks.impl;

import io.codeleaf.common.utils.Types;
import io.codeleaf.oerm.tasks.UpdateEntityTask;

import java.util.Objects;

public final class UpdateEntityTaskImpl<E, D> extends AbstractDataTask<D, Void> implements UpdateEntityTask<E, D, Void> {

    private final Class<E> entityType;
    private final E entity;

    private UpdateEntityTaskImpl(Class<E> entityType, E entity, D dataType) {
        super(dataType, Void.class);
        this.entityType = entityType;
        this.entity = entity;
    }

    @Override
    public Class<E> getEntityType() {
        return entityType;
    }

    @Override
    public E getEntity() {
        return entity;
    }

    public static final class Builder<E, D>
            extends AbstractDataTask.Builder<Builder<E, D>, UpdateEntityTaskImpl<E, D>, D, Void>
            implements UpdateEntityTask.Builder<Builder<E, D>, UpdateEntityTaskImpl<E, D>, E, D, Void> {

        private final Class<E> entityType;
        private E entity;

        public Builder(Class<E> entityType, D dataType) {
            super(dataType);
            this.entityType = entityType;
        }

        @Override
        public Builder<E, D> withEntity(E entity) {
            Objects.requireNonNull(entity);
            this.entity = entity;
            return this;
        }

        @Override
        protected void validate() {
            super.validate();
            if (entity == null) {
                throw new IllegalStateException("Entity must be set!");
            }
            if (entityType != null && !entityType.isAssignableFrom(entity.getClass())) {
                throw new IllegalStateException("Entity must have the specified entity type!");
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
