package io.codeleaf.oerm.impl.tasks.data;

import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.impl.tasks.AbstractEntityBasedTask;
import io.codeleaf.oerm.tasks.data.AddEntityTask;

public class GenericAddEntityTask<E, K, D, F, V, S>
        extends AbstractEntityBasedTask<E, K, D, F, V, S, K>
        implements AddEntityTask<E, K, D, F, V, S> {

    public GenericAddEntityTask(DataModelTypes<E, K, D, F, V, S> dataModelTypes, D dataType, E entity) {
        super(dataModelTypes, dataModelTypes.getEntityIdType(), dataType, entity);
    }

    public static class Builder<E, K, D, F, V, S>
            extends AbstractEntityBasedTask.Builder
            <
                    Builder<E, K, D, F, V, S>,
                    GenericAddEntityTask<E, K, D, F, V, S>,
                    E, K, D, F, V, S, K>
            implements AddEntityTask.Builder
            <
                    Builder<E, K, D, F, V, S>,
                    GenericAddEntityTask<E, K, D, F, V, S>,
                    E, K, D, F, V, S> {

        public Builder(DataModelTypes<E, K, D, F, V, S> dataModelTypes) {
            super(dataModelTypes);
        }

        @Override
        public GenericAddEntityTask<E, K, D, F, V, S> build() {
            validate();
            return new GenericAddEntityTask<>(dataModelTypes, dataType, entity);
        }
    }
}
