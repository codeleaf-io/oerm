package io.codeleaf.oerm.impl.tasks.data;

import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.Count;
import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.impl.tasks.AbstractSelectTask;
import io.codeleaf.oerm.tasks.data.CountEntitiesTask;

public class GenericCountEntitiesTask<E, K, D, F, V, S>
        extends AbstractSelectTask<E, K, D, F, V, S, Count>
        implements CountEntitiesTask<E, K, D, F, V, S> {

    public GenericCountEntitiesTask(DataModelTypes<E, K, D, F, V, S> dataModelTypes, D dataType, Selection selection) {
        super(dataModelTypes, Count.class, dataType, selection);
    }

    public static class Builder<E, K, D, F, V, S>
            extends AbstractSelectTask.Builder
            <
                    Builder<E, K, D, F, V, S>,
                    GenericCountEntitiesTask<E, K, D, F, V, S>,
                    E, K, D, F, V, S, Count>
            implements CountEntitiesTask.Builder
            <
                    Builder<E, K, D, F, V, S>,
                    GenericCountEntitiesTask<E, K, D, F, V, S>,
                    E, K, D, F, V, S> {

        public Builder(DataModelTypes<E, K, D, F, V, S> dataModelTypes) {
            super(dataModelTypes);
        }

        @Override
        public GenericCountEntitiesTask<E, K, D, F, V, S> build() {
            validate();
            return new GenericCountEntitiesTask<>(dataModelTypes, dataType, selection);
        }
    }
}
