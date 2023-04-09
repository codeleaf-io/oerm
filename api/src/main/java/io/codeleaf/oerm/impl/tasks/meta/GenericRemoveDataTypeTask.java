package io.codeleaf.oerm.impl.tasks.meta;

import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.impl.tasks.AbstractTypeBasedTask;
import io.codeleaf.oerm.tasks.meta.RemoveDataTypeTask;

public class GenericRemoveDataTypeTask<E, K, D, F, V, S>
        extends AbstractTypeBasedTask<E, K, D, F, V, S, Boolean>
        implements RemoveDataTypeTask<E, K, D, F, V, S> {

    public GenericRemoveDataTypeTask(DataModelTypes<E, K, D, F, V, S> dataModelTypes, D dataType) {
        super(dataModelTypes, Boolean.class, dataType);
    }

    public static class Builder<E, K, D, F, V, S>
            extends AbstractTypeBasedTask.Builder
            <
                    Builder<E, K, D, F, V, S>,
                    GenericRemoveDataTypeTask<E, K, D, F, V, S>,
                    E, K, D, F, V, S, Boolean>
            implements RemoveDataTypeTask.Builder
            <
                    Builder<E, K, D, F, V, S>,
                    GenericRemoveDataTypeTask<E, K, D, F, V, S>,
                    E, K, D, F, V, S> {

        public Builder(DataModelTypes<E, K, D, F, V, S> dataModelTypes) {
            super(dataModelTypes);
        }

        @Override
        public GenericRemoveDataTypeTask<E, K, D, F, V, S> build() {
            validate();
            return new GenericRemoveDataTypeTask<>(dataModelTypes, dataType);
        }
    }
}
