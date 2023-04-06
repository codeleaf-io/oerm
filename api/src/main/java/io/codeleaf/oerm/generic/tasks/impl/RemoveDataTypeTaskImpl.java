package io.codeleaf.oerm.generic.tasks.impl;

import io.codeleaf.oerm.generic.tasks.RemoveDataTypeTask;

public final class RemoveDataTypeTaskImpl<D> extends AbstractDataTypeTask<D, Boolean>
        implements RemoveDataTypeTask<D> {

    private final D dataType;

    public RemoveDataTypeTaskImpl(D dataType) {
        super(Boolean.class);
        this.dataType = dataType;
    }

    @Override
    public D getDataType() {
        return dataType;
    }

    public static final class Builder<D, S>
            extends AbstractDataTypeTask.Builder<Builder<D, S>, RemoveDataTypeTaskImpl<D>, D, Boolean>
            implements RemoveDataTypeTask.Builder<Builder<D, S>, RemoveDataTypeTaskImpl<D>, D> {

        private D dataType;

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
        }

        @Override
        public RemoveDataTypeTaskImpl<D> build() {
            validate();
            return new RemoveDataTypeTaskImpl<>(dataType);
        }
    }

}
