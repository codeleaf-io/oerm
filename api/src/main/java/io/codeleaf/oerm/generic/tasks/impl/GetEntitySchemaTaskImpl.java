package io.codeleaf.oerm.generic.tasks.impl;

import io.codeleaf.oerm.generic.tasks.GetEntitySchemaTask;

public final class GetEntitySchemaTaskImpl<D, S> extends AbstractDataTypeTask<D, S>
        implements GetEntitySchemaTask<D, S> {

    private final D dataType;
    private final Class<S> entitySchemaType;

    public GetEntitySchemaTaskImpl(D dataType, Class<S> entitySchemaType, Class<D> dataTypeType) {
        super(dataTypeType, entitySchemaType);
        this.dataType = dataType;
        this.entitySchemaType = entitySchemaType;
    }

    @Override
    public D getDataType() {
        return dataType;
    }

    @Override
    public Class<S> getEntitySchemaType() {
        return entitySchemaType;
    }

    public static final class Builder<D, S>
            extends AbstractDataTypeTask.Builder<Builder<D, S>, GetEntitySchemaTaskImpl<D, S>, D, S>
            implements GetEntitySchemaTask.Builder<Builder<D, S>, GetEntitySchemaTaskImpl<D, S>, D, S> {

        private final Class<S> entitySchemaType;
        private D dataType;

        public Builder(Class<D> dataTypeType, Class<S> entitySchemaType) {
            super(dataTypeType);
            this.entitySchemaType = entitySchemaType;
        }

        @Override
        public Builder<D, S> withDataType(D dataType) {
            this.dataType = dataType;
            return this;
        }

        @Override
        protected void validate() {
            super.validate();
            if (!dataTypeType.isInstance(dataType)) {
                throw new IllegalStateException();
            }
            if (entitySchemaType == null) {
                throw new IllegalStateException();
            }
        }

        @Override
        public GetEntitySchemaTaskImpl<D, S> build() {
            validate();
            return new GetEntitySchemaTaskImpl<>(dataType, entitySchemaType, dataTypeType);
        }
    }

}
