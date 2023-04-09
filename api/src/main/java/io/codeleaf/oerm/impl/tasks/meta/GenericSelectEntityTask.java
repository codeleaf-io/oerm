package io.codeleaf.oerm.impl.tasks.meta;

import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.impl.tasks.AbstractTypeBasedTask;
import io.codeleaf.oerm.tasks.meta.SelectEntityTask;

public class GenericSelectEntityTask<E, K, D, F, V, S>
        extends AbstractTypeBasedTask<E, K, D, F, V, S, Selection>
        implements SelectEntityTask<E, K, D, F, V, S> {

    private final K entityId;

    public GenericSelectEntityTask(DataModelTypes<E, K, D, F, V, S> dataModelTypes, D dataType, K entityId) {
        super(dataModelTypes, Selection.class, dataType);
        this.entityId = entityId;
    }

    @Override
    public K getEntityId() {
        return entityId;
    }

    public static class Builder<E, K, D, F, V, S>
            extends AbstractTypeBasedTask.Builder
            <
                    Builder<E, K, D, F, V, S>,
                    GenericSelectEntityTask<E, K, D, F, V, S>,
                    E, K, D, F, V, S, Selection>
            implements SelectEntityTask.Builder
            <
                    Builder<E, K, D, F, V, S>,
                    GenericSelectEntityTask<E, K, D, F, V, S>,
                    E, K, D, F, V, S> {

        protected K entityId;

        public Builder(DataModelTypes<E, K, D, F, V, S> dataModelTypes) {
            super(dataModelTypes);
        }

        @Override
        public Builder<E, K, D, F, V, S> withEntityId(K entityId) {
            this.entityId = entityId;
            return this;
        }

        @Override
        protected void validate() {
            super.validate();
            if (!dataModelTypes.getEntityIdType().isInstance(entityId)) {
                throw new IllegalStateException();
            }
        }

        @Override
        public GenericSelectEntityTask<E, K, D, F, V, S> build() {
            validate();
            return new GenericSelectEntityTask<>(dataModelTypes, dataType, entityId);
        }
    }

}
