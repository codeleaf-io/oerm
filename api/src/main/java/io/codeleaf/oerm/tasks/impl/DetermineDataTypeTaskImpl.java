package io.codeleaf.oerm.tasks.impl;

import io.codeleaf.oerm.tasks.DetermineDataTypeTask;

public final class DetermineDataTypeTaskImpl<E, D> extends AbstractDataTypeTask<D, D>
        implements DetermineDataTypeTask<E, D> {

    private final Class<E> entityType;
    private final E entity;

    public DetermineDataTypeTaskImpl(Class<E> entityType, Class<D> dataTypeType, E entity) {
        super(dataTypeType, dataTypeType);
        this.entityType = entityType;
        this.entity = entity;
    }

    @Override
    public E getEntity() {
        return entity;
    }

    @Override
    public Class<E> getEntityType() {
        return entityType;
    }

    public static final class Builder<E, D> extends AbstractDataTypeTask.Builder<
            Builder<E, D>,
            DetermineDataTypeTaskImpl<E, D>,
            D,
            D> implements DetermineDataTypeTask.Builder<Builder<E, D>, DetermineDataTypeTaskImpl<E, D>, E, D> {

        private final Class<E> entityType;
        private E entity;

        public Builder(Class<E> entityType, Class<D> dataTypeType) {
            super(dataTypeType);
            this.entityType = entityType;
        }

        @Override
        public Builder<E, D> withEntity(E entity) {
            this.entity = entity;
            return this;
        }

        @Override
        protected void validate() {
            super.validate();
            if (entityType == null) {
                throw new IllegalStateException();
            }
            if (!entityType.isInstance(entity)) {
                throw new IllegalStateException();
            }
        }

        @Override
        public DetermineDataTypeTaskImpl<E, D> build() {
            validate();
            return new DetermineDataTypeTaskImpl<>(entityType, dataTypeType, entity);
        }
    }

}
