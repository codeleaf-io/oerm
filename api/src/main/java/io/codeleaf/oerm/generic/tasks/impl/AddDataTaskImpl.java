package io.codeleaf.oerm.generic.tasks.impl;

import io.codeleaf.oerm.generic.tasks.AddDataTypeTask;

public final class AddDataTaskImpl<D, S> extends AbstractDataTypeTask<D, Void>
        implements AddDataTypeTask<D, S> {

    private final D dataType;
    private final S schema;

    public AddDataTaskImpl(D dataType, S schema) {
        super(Void.class);
        this.dataType = dataType;
        this.schema = schema;
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
            extends AbstractDataTypeTask.Builder<Builder<D, S>, AddDataTaskImpl<D, S>, D, Void>
            implements AddDataTypeTask.Builder<Builder<D, S>, AddDataTaskImpl<D, S>, D, S> {

        private D dataType;
        private S schema;

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
            if (dataType == null) {
                throw new IllegalStateException();
            }
            if (schema == null) {
                throw new IllegalStateException();
            }
        }

        @Override
        public AddDataTaskImpl<D, S> build() {
            validate();
            return new AddDataTaskImpl<>(dataType, schema);
        }
    }

}
