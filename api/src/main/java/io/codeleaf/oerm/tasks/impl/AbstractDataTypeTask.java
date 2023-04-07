package io.codeleaf.oerm.tasks.impl;

import io.codeleaf.oerm.tasks.DataTypeTask;

public abstract class AbstractDataTypeTask<D, O> implements DataTypeTask<D, O> {

    private final Class<D> dataTypeType;
    private final Class<O> outputType;

    protected AbstractDataTypeTask(Class<D> dataTypeType, Class<O> outputType) {
        this.dataTypeType = dataTypeType;
        this.outputType = outputType;
    }

    @Override
    public Class<D> getDataTypeType() {
        return dataTypeType;
    }

    @Override
    public Class<O> getOutputType() {
        return outputType;
    }

    public static abstract class Builder<
            B extends AbstractDataTypeTask.Builder<B, T, D, O>,
            T extends AbstractDataTypeTask<D, O>,
            D,
            O
            > implements DataTypeTask.Builder<B, T, D, O> {

        protected final Class<D> dataTypeType;

        public Builder(Class<D> dataTypeType) {
            this.dataTypeType = dataTypeType;
        }

        protected void validate() {
            if (dataTypeType == null) {
                throw new IllegalStateException();
            }
        }
    }

}
