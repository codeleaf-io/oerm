package io.codeleaf.oerm.impl.tasks;

import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.tasks.DatabaseTask;

public abstract class AbstractDatabaseTask<E, K, D, F, V, S, O>
        implements DatabaseTask<E, K, D, F, V, S, O> {

    private final DataModelTypes<E, K, D, F, V, S> dataModelTypes;
    private final Class<O> outputType;

    public AbstractDatabaseTask(DataModelTypes<E, K, D, F, V, S> dataModelTypes, Class<O> outputType) {
        this.dataModelTypes = dataModelTypes;
        this.outputType = outputType;
    }

    @Override
    public DataModelTypes<E, K, D, F, V, S> getDataModelTypes() {
        return dataModelTypes;
    }

    @Override
    public Class<O> getOutputType() {
        return outputType;
    }

    public static abstract class Builder<
            B extends AbstractDatabaseTask.Builder<B, T, E, K, D, F, V, S, O>,
            T extends AbstractDatabaseTask<E, K, D, F, V, S, O>,
            E, K, D, F, V, S, O>
            implements DatabaseTask.Builder<B, T, E, K, D, F, V, S, O> {

        protected final DataModelTypes<E, K, D, F, V, S> dataModelTypes;

        public Builder(DataModelTypes<E, K, D, F, V, S> dataModelTypes) {
            this.dataModelTypes = dataModelTypes;
        }

        protected void validate() {
            if (dataModelTypes == null) {
                throw new IllegalStateException();
            }
        }
    }
}
