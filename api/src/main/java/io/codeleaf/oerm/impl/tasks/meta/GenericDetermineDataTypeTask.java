package io.codeleaf.oerm.impl.tasks.meta;

import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.impl.tasks.AbstractDatabaseTask;
import io.codeleaf.oerm.tasks.meta.DetermineDataTypeTask;

public class GenericDetermineDataTypeTask<E, K, D, F, V, S>
        extends AbstractDatabaseTask<E, K, D, F, V, S, D>
        implements DetermineDataTypeTask<E, K, D, F, V, S> {

    private final E entity;

    public GenericDetermineDataTypeTask(DataModelTypes<E, K, D, F, V, S> dataModelTypes, E entity) {
        super(dataModelTypes, dataModelTypes.getDataTypeType());
        this.entity = entity;
    }

    @Override
    public E getEntity() {
        return entity;
    }

    public static class Builder<E, K, D, F, V, S>
            extends AbstractDatabaseTask.Builder
            <
                    Builder<E, K, D, F, V, S>,
                    GenericDetermineDataTypeTask<E, K, D, F, V, S>,
                    E, K, D, F, V, S, D>
            implements DetermineDataTypeTask.Builder
            <
                    Builder<E, K, D, F, V, S>,
                    GenericDetermineDataTypeTask<E, K, D, F, V, S>,
                    E, K, D, F, V, S> {

        protected E entity;

        public Builder(DataModelTypes<E, K, D, F, V, S> dataModelTypes) {
            super(dataModelTypes);
        }

        @Override
        public Builder<E, K, D, F, V, S> withEntity(E entity) {
            this.entity = entity;
            return this;
        }

        @Override
        protected void validate() {
            super.validate();
            if (!dataModelTypes.getEntityType().isInstance(entity)) {
                throw new IllegalStateException();
            }
        }

        @Override
        public GenericDetermineDataTypeTask<E, K, D, F, V, S> build() {
            validate();
            return new GenericDetermineDataTypeTask<>(dataModelTypes, entity);
        }
    }
}
