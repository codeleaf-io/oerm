package io.codeleaf.oerm.impl.tasks;

import io.codeleaf.common.utils.Types;
import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.tasks.TypeBasedTask;

public abstract class AbstractTypeBasedTask<E, K, D, F, V, S, O>
        extends AbstractDatabaseTask<E, K, D, F, V, S, O>
        implements TypeBasedTask<E, K, D, F, V, S, O> {

    private final D dataType;

    protected AbstractTypeBasedTask(DataModelTypes<E, K, D, F, V, S> dataModelTypes, Class<O> outputType, D dataType) {
        super(dataModelTypes, outputType);
        this.dataType = dataType;
    }

    @Override
    public D getDataType() {
        return dataType;
    }

    public static abstract class Builder<
            B extends AbstractTypeBasedTask.Builder<B, T, E, K, D, F, V, S, O>,
            T extends AbstractTypeBasedTask<E, K, D, F, V, S, O>,
            E, K, D, F, V, S, O>
            extends AbstractDatabaseTask.Builder<B, T, E, K, D, F, V, S, O>
            implements TypeBasedTask.Builder<B, T, E, K, D, F, V, S, O> {

        protected D dataType;

        public Builder(DataModelTypes<E, K, D, F, V, S> dataModelTypes) {
            super(dataModelTypes);
        }

        public B withDataType(D dataType) {
            this.dataType = dataType;
            return Types.cast(this);
        }

        @Override
        protected void validate() {
            super.validate();
            if (!dataModelTypes.getDataTypeType().isInstance(dataType)) {
                throw new IllegalStateException();
            }
        }
    }
}
