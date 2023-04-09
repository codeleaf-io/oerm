package io.codeleaf.oerm.impl.tasks;

import io.codeleaf.common.utils.Types;
import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.tasks.EntityBasedTask;

public abstract class AbstractEntityBasedTask<E, K, D, F, V, S, O>
        extends AbstractTypeBasedTask<E, K, D, F, V, S, O>
        implements EntityBasedTask<E, K, D, F, V, S, O> {

    private final E entity;

    protected AbstractEntityBasedTask(DataModelTypes<E, K, D, F, V, S> dataModelTypes, Class<O> outputType, D dataType, E entity) {
        super(dataModelTypes, outputType, dataType);
        this.entity = entity;
    }

    @Override
    public E getEntity() {
        return entity;
    }

    public static abstract class Builder<
            B extends Builder<B, T, E, K, D, F, V, S, O>,
            T extends AbstractEntityBasedTask<E, K, D, F, V, S, O>,
            E, K, D, F, V, S, O>
            extends AbstractTypeBasedTask.Builder<B, T, E, K, D, F, V, S, O>
            implements EntityBasedTask.Builder<B, T, E, K, D, F, V, S, O> {

        protected E entity;

        public Builder(DataModelTypes<E, K, D, F, V, S> dataModelTypes) {
            super(dataModelTypes);
        }

        @Override
        public B withEntity(E entity) {
            this.entity = entity;
            return Types.cast(this);
        }

        @Override
        protected void validate() {
            super.validate();
            if (!dataModelTypes.getEntityType().isInstance(entity)) {
                throw new IllegalStateException();
            }
        }
    }
}
