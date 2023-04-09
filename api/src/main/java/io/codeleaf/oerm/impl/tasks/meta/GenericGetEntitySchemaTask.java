package io.codeleaf.oerm.impl.tasks.meta;

import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.impl.tasks.AbstractTypeBasedTask;
import io.codeleaf.oerm.tasks.meta.GetEntitySchemaTask;

public class GenericGetEntitySchemaTask<E, K, D, F, V, S>
        extends AbstractTypeBasedTask<E, K, D, F, V, S, S>
        implements GetEntitySchemaTask<E, K, D, F, V, S> {

    public GenericGetEntitySchemaTask(DataModelTypes<E, K, D, F, V, S> dataModelTypes, D dataType) {
        super(dataModelTypes, dataModelTypes.getEntitySchemaType(), dataType);
    }

    public static class Builder<E, K, D, F, V, S>
            extends AbstractTypeBasedTask.Builder
            <
                    Builder<E, K, D, F, V, S>,
                    GenericGetEntitySchemaTask<E, K, D, F, V, S>,
                    E, K, D, F, V, S, S>
            implements GetEntitySchemaTask.Builder
            <
                    Builder<E, K, D, F, V, S>,
                    GenericGetEntitySchemaTask<E, K, D, F, V, S>,
                    E, K, D, F, V, S> {

        public Builder(DataModelTypes<E, K, D, F, V, S> dataModelTypes) {
            super(dataModelTypes);
        }

        @Override
        public GenericGetEntitySchemaTask<E, K, D, F, V, S> build() {
            validate();
            return new GenericGetEntitySchemaTask<>(dataModelTypes, dataType);
        }
    }

}
