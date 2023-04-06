package io.codeleaf.oerm.generic.tasks.impl;

import io.codeleaf.oerm.generic.tasks.DetermineDataTypeTask;

public final class DetermineDataTypeTaskImpl<E, D> extends AbstractDataTypeTask<D, D>
        implements DetermineDataTypeTask<E, D> {

    private final E entity;

    public DetermineDataTypeTaskImpl(Class<D> dataTypeClass, E entity) {
        super(dataTypeClass);
        this.entity = entity;
    }

    @Override
    public E getEntity() {
        return entity;
    }

    public static final class Builder<E, D> extends AbstractDataTypeTask.Builder<
            Builder<E, D>,
            DetermineDataTypeTaskImpl<E, D>,
            D,
            D> implements DetermineDataTypeTask.Builder<Builder<E, D>, DetermineDataTypeTaskImpl<E, D>, E, D> {

        private final Class<D> dataTypeClass;
        private E entity;

        public Builder(Class<D> dataTypeClass) {
            this.dataTypeClass = dataTypeClass;
        }

        @Override
        public Builder<E, D> withEntity(E entity) {
            this.entity = entity;
            return this;
        }

        @Override
        protected void validate() {
            super.validate();
            if (entity == null) {
                throw new IllegalStateException();
            }
            if (dataTypeClass == null) {
                throw new IllegalStateException();
            }
        }

        @Override
        public DetermineDataTypeTaskImpl<E, D> build() {
            validate();
            return new DetermineDataTypeTaskImpl<>(dataTypeClass, entity);
        }
    }

}
