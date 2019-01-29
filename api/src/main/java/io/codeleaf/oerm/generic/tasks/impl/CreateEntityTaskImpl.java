package io.codeleaf.oerm.generic.tasks.impl;

import io.codeleaf.oerm.generic.tasks.CreateEntityTask;

import java.util.Objects;

public final class CreateEntityTaskImpl<E, K, D> extends AbstractCreateTask<K, D> implements CreateEntityTask<E, K, D> {

    private final Class<E> entityType;
    private final E entity;

    private CreateEntityTaskImpl(Class<E> entityType, D dataType, E entity, Class<K> entityIdType) {
        super(entityIdType, dataType);
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

    public static final class Builder<E, K, D>
            extends AbstractCreateTask.Builder<Builder<E, K, D>, CreateEntityTaskImpl<E, K, D>, K, D>
            implements CreateEntityTask.Builder<Builder<E, K, D>, CreateEntityTaskImpl<E, K, D>, E, K, D> {

        private Class<E> entityType;
        private E entity;

        public Builder() {
        }

        public Builder(Class<E> entityType, Class<K> entityIdType, D dataType) {
            super(entityIdType, dataType);
            this.entityType = entityType;
        }

        @Override
        public Builder<E, K, D> withEntityType(Class<E> entityType) {
            Objects.requireNonNull(entityType);
            this.entityType = entityType;
            return this;
        }

        @Override
        public Builder<E, K, D> withEntity(E entity) {
            Objects.requireNonNull(entity);
            this.entity = entity;
            return this;
        }

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
        public CreateEntityTaskImpl<E, K, D> build() {
            validate();
            return new CreateEntityTaskImpl<>(entityType, dataType, entity, entityIdType);
        }
    }
}
