package io.codeleaf.oerm.impl.tasks.data;

import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.impl.tasks.AbstractEntityBasedTask;
import io.codeleaf.oerm.tasks.data.UpdateEntityTask;

public class GenericUpdateEntityTask<E, K, D, F, V, S>
        extends AbstractEntityBasedTask<E, K, D, F, V, S, Void>
        implements UpdateEntityTask<E, K, D, F, V, S> {

    public GenericUpdateEntityTask(DataModelTypes<E, K, D, F, V, S> dataModelTypes, D dataType, E entity) {
        super(dataModelTypes, Void.class, dataType, entity);
    }

    public static class Builder<E, K, D, F, V, S>
            extends AbstractEntityBasedTask.Builder
            <
                    Builder<E, K, D, F, V, S>,
                    GenericUpdateEntityTask<E, K, D, F, V, S>,
                    E, K, D, F, V, S, Void>
            implements UpdateEntityTask.Builder
            <
                    Builder<E, K, D, F, V, S>,
                    GenericUpdateEntityTask<E, K, D, F, V, S>,
                    E, K, D, F, V, S> {

        public Builder(DataModelTypes<E, K, D, F, V, S> dataModelTypes) {
            super(dataModelTypes);
        }

        @Override
        public GenericUpdateEntityTask<E, K, D, F, V, S> build() {
            validate();
            return new GenericUpdateEntityTask<>(dataModelTypes, dataType, entity);
        }
    }
}
