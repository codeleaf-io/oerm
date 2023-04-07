package io.codeleaf.oerm.tasks.impl;

import io.codeleaf.oerm.tasks.AddEntityTask;

import java.util.Objects;

public final class AddEntityTaskImpl<E, K, D> extends AbstractAddTask<K, D> implements AddEntityTask<E, K, D> {

    private final Class<E> entityType;
    private final E entity;

    private AddEntityTaskImpl(Class<E> entityType, D dataType, E entity, Class<K> entityIdType) {
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
            extends AbstractAddTask.Builder<Builder<E, K, D>, AddEntityTaskImpl<E, K, D>, K, D>
            implements AddEntityTask.Builder<Builder<E, K, D>, AddEntityTaskImpl<E, K, D>, E, K, D> {

        private final Class<E> entityType;
        private E entity;

        public Builder(Class<E> entityType, Class<K> entityIdType, D dataType) {
            super(entityIdType, dataType);
            this.entityType = entityType;
        }

        @Override
        public Builder<E, K, D> withEntity(E entity) {
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
        public AddEntityTaskImpl<E, K, D> build() {
            validate();
            return new AddEntityTaskImpl<>(entityType, dataType, entity, entityIdType);
        }
    }
}
