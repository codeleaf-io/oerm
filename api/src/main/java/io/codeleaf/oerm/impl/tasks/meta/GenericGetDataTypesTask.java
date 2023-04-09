package io.codeleaf.oerm.impl.tasks.meta;

import io.codeleaf.common.utils.Types;
import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.impl.tasks.AbstractDatabaseTask;
import io.codeleaf.oerm.tasks.meta.GetDataTypesTask;

import java.util.Set;

public class GenericGetDataTypesTask<E, K, D, F, V, S>
        extends AbstractDatabaseTask<E, K, D, F, V, S, Set<D>>
        implements GetDataTypesTask<E, K, D, F, V, S> {

    public GenericGetDataTypesTask(DataModelTypes<E, K, D, F, V, S> dataModelTypes) {
        super(dataModelTypes, Types.cast(Set.class));
    }

    public static class Builder<E, K, D, F, V, S>
            extends AbstractDatabaseTask.Builder
            <
                    Builder<E, K, D, F, V, S>,
                    GenericGetDataTypesTask<E, K, D, F, V, S>,
                    E, K, D, F, V, S, Set<D>>
            implements GetDataTypesTask.Builder
            <
                    Builder<E, K, D, F, V, S>,
                    GenericGetDataTypesTask<E, K, D, F, V, S>,
                    E, K, D, F, V, S> {

        public Builder(DataModelTypes<E, K, D, F, V, S> dataModelTypes) {
            super(dataModelTypes);
        }

        @Override
        public GenericGetDataTypesTask<E, K, D, F, V, S> build() {
            validate();
            return new GenericGetDataTypesTask<>(dataModelTypes);
        }
    }

}
