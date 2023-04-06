package io.codeleaf.oerm.generic.tasks.impl;

import io.codeleaf.oerm.generic.tasks.DataTypeTask;

public abstract class AbstractDataTypeTask<D, O> implements DataTypeTask<D, O> {

    private final Class<O> outputType;

    protected AbstractDataTypeTask(Class<O> outputType) {
        this.outputType = outputType;
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

        protected void validate() {
        }
    }

}
