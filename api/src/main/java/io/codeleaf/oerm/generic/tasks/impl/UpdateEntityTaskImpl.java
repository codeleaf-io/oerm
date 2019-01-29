package io.codeleaf.oerm.generic.tasks.impl;

import io.codeleaf.common.utils.Types;
import io.codeleaf.oerm.generic.tasks.UpdateEntityTask;
import io.codeleaf.modeling.selection.Selection;

import java.util.Objects;

public final class UpdateEntityTaskImpl<E, D, F, V> extends AbstractSelectTask<D, F, V, Void> implements UpdateEntityTask<E, D, F, V, Void> {

    private final Class<E> entityType;
    private final E entity;

    private UpdateEntityTaskImpl(Selection selection, Class<F> fieldNameType, Class<V> fieldValueType, D dataType, Class<E> entityType, E entity) {
        super(selection, fieldNameType, fieldValueType, dataType, Void.class);
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

    public static final class Builder<E, D, F, V>
            extends AbstractSelectTask.Builder<Builder<E, D, F, V>, UpdateEntityTaskImpl<E, D, F, V>, D, F, V, Void>
            implements UpdateEntityTask.Builder<Builder<E, D, F, V>, UpdateEntityTaskImpl<E, D, F, V>, E, D, F, V, Void> {

        private Class<E> entityType;
        private E entity;

        public Builder() {
        }

        public Builder(Class<E> entityType, D dataType, Class<F> fieldNameType, Class<V> fieldValueType) {
            super(dataType, fieldNameType, fieldValueType);
            this.entityType = entityType;
        }

        @Override
        public Builder<E, D, F, V> withEntityType(Class<E> entityType) {
            Objects.requireNonNull(entityType);
            this.entityType = entityType;
            return this;
        }

        @Override
        public Builder<E, D, F, V> withEntity(E entity) {
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
        public UpdateEntityTaskImpl<E, D, F, V> build() {
            validate();
            return new UpdateEntityTaskImpl<>(selection, fieldNameType, fieldValueType, dataType,
                    entityType == null ? Types.cast(entity.getClass()) : entityType,
                    entity);
        }
    }
}
