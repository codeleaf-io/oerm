package io.codeleaf.oerm.generic.tasks.impl;

import io.codeleaf.oerm.generic.tasks.AddDataTypeTask;

public final class AddDataTypeTaskImpl<D, S> extends AbstractDataTypeTask<D, Void>
        implements AddDataTypeTask<D, S> {

    private final Class<S> entitySchemaType;
    private final D dataType;
    private final S schema;

    public AddDataTypeTaskImpl(Class<D> dataTypeType, Class<S> entitySchemaType, D dataType, S schema) {
        super(dataTypeType, Void.class);
        this.entitySchemaType = entitySchemaType;
        this.dataType = dataType;
        this.schema = schema;
    }

    @Override
    public Class<S> getEntitySchemaType() {
        return entitySchemaType;
    }

    @Override
    public D getDataType() {
        return dataType;
    }

    @Override
    public S getSchema() {
        return schema;
    }

    public static final class Builder<D, S>
            extends AbstractDataTypeTask.Builder<Builder<D, S>, AddDataTypeTaskImpl<D, S>, D, Void>
            implements AddDataTypeTask.Builder<Builder<D, S>, AddDataTypeTaskImpl<D, S>, D, S> {

        private final Class<S> entitySchemaType;
        private D dataType;
        private S schema;

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
        public Builder<D, S> withSchema(S schema) {
            this.schema = schema;
            return this;
        }

        @Override
        protected void validate() {
            super.validate();
            if (!dataTypeType.isInstance(dataType)) {
                throw new IllegalStateException();
            }
            if (entitySchemaType == null || !entitySchemaType.isInstance(schema)) {
                throw new IllegalStateException();
            }
        }

        @Override
        public AddDataTypeTaskImpl<D, S> build() {
            validate();
            return new AddDataTypeTaskImpl<>(dataTypeType, entitySchemaType, dataType, schema);
        }
    }

}
