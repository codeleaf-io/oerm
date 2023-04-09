package io.codeleaf.oerm.impl.tasks.data;

import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.impl.tasks.AbstractSelectTask;
import io.codeleaf.oerm.tasks.data.RetrieveEntityTask;

public class GenericRetrieveEntityTask<E, K, D, F, V, S>
        extends AbstractSelectTask<E, K, D, F, V, S, E>
        implements RetrieveEntityTask<E, K, D, F, V, S> {

    public GenericRetrieveEntityTask(DataModelTypes<E, K, D, F, V, S> dataModelTypes, D dataType, Selection selection) {
        super(dataModelTypes, dataModelTypes.getEntityType(), dataType, selection);
    }

    public static class Builder<E, K, D, F, V, S>
            extends AbstractSelectTask.Builder
            <
                    Builder<E, K, D, F, V, S>,
                    GenericRetrieveEntityTask<E, K, D, F, V, S>,
                    E, K, D, F, V, S, E>
            implements RetrieveEntityTask.Builder
            <
                    Builder<E, K, D, F, V, S>,
                    GenericRetrieveEntityTask<E, K, D, F, V, S>,
                    E, K, D, F, V, S> {

        public Builder(DataModelTypes<E, K, D, F, V, S> dataModelTypes) {
            super(dataModelTypes);
        }

        @Override
        public GenericRetrieveEntityTask<E, K, D, F, V, S> build() {
            validate();
            return new GenericRetrieveEntityTask<>(dataModelTypes, dataType, selection);
        }
    }
}
