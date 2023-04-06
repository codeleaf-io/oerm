package io.codeleaf.oerm.generic.tasks.impl;

import io.codeleaf.oerm.generic.tasks.DataTask;

public abstract class AbstractDataTask<D, O> implements DataTask<D, O> {

    private final D dataType;
    private final Class<O> outputType;

    protected AbstractDataTask(D dataType, Class<O> outputType) {
        this.dataType = dataType;
        this.outputType = outputType;
    }

    @Override
    public D getDataType() {
        return dataType;
    }

    @Override
    public Class<O> getOutputType() {
        return outputType;
    }

    public static abstract class Builder<
            B extends AbstractDataTask.Builder<B, T, D, O>,
            T extends AbstractDataTask<D, O>,
            D,
            O
            > implements DataTask.Builder<B, T, D, O> {

        protected final D dataType;

        public Builder(D dataType) {
            this.dataType = dataType;
        }

        protected void validate() {
            if (dataType == null) {
                throw new IllegalStateException();
            }
        }

    }

}
