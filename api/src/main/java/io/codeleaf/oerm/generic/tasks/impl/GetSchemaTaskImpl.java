package io.codeleaf.oerm.generic.tasks.impl;

import io.codeleaf.oerm.generic.tasks.GetSchemaTask;

public final class GetSchemaTaskImpl<D, S> extends AbstractDataTypeTask<D, S>
        implements GetSchemaTask<D, S> {

    private final D dataType;

    public GetSchemaTaskImpl(D dataType, Class<S> schemaType) {
        super(schemaType);
        this.dataType = dataType;
    }

    @Override
    public D getDataType() {
        return dataType;
    }

    public static final class Builder<D, S>
            extends AbstractDataTypeTask.Builder<Builder<D, S>, GetSchemaTaskImpl<D, S>, D, S>
            implements GetSchemaTask.Builder<Builder<D, S>, GetSchemaTaskImpl<D, S>, D, S> {

        private final Class<S> schemaTypeClass;
        private D dataType;

        public Builder(Class<S> schemaTypeClass) {
            this.schemaTypeClass = schemaTypeClass;
        }

        @Override
        public Builder<D, S> withDataType(D dataType) {
            this.dataType = dataType;
            return this;
        }

        @Override
        protected void validate() {
            super.validate();
            if (dataType == null) {
                throw new IllegalStateException();
            }
            if (schemaTypeClass == null) {
                throw new IllegalStateException();
            }
        }

        @Override
        public GetSchemaTaskImpl<D, S> build() {
            validate();
            return new GetSchemaTaskImpl<>(dataType, schemaTypeClass);
        }
    }

}
