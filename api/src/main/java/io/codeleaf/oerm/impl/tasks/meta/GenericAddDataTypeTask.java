package io.codeleaf.oerm.impl.tasks.meta;

import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.impl.tasks.AbstractTypeBasedTask;
import io.codeleaf.oerm.tasks.meta.AddDataTypeTask;

public class GenericAddDataTypeTask<E, K, D, F, V, S>
        extends AbstractTypeBasedTask<E, K, D, F, V, S, Boolean>
        implements AddDataTypeTask<E, K, D, F, V, S> {

    private final S schema;

    public GenericAddDataTypeTask(DataModelTypes<E, K, D, F, V, S> dataModelTypes, D dataType, S schema) {
        super(dataModelTypes, Boolean.class, dataType);
        this.schema = schema;
    }

    @Override
    public S getSchema() {
        return schema;
    }

    public static class Builder<E, K, D, F, V, S>
            extends AbstractTypeBasedTask.Builder
            <
                    Builder<E, K, D, F, V, S>,
                    GenericAddDataTypeTask<E, K, D, F, V, S>,
                    E, K, D, F, V, S, Boolean>
            implements AddDataTypeTask.Builder
            <
                    Builder<E, K, D, F, V, S>,
                    GenericAddDataTypeTask<E, K, D, F, V, S>,
                    E, K, D, F, V, S> {

        protected S schema;

        public Builder(DataModelTypes<E, K, D, F, V, S> dataModelTypes) {
            super(dataModelTypes);
        }

        @Override
        public Builder<E, K, D, F, V, S> withSchema(S schema) {
            this.schema = schema;
            return this;
        }

        @Override
        protected void validate() {
            super.validate();
            if (!dataModelTypes.getEntitySchemaType().isInstance(schema)) {
                throw new IllegalStateException();
            }
        }

        @Override
        public GenericAddDataTypeTask<E, K, D, F, V, S> build() {
            validate();
            return new GenericAddDataTypeTask<>(dataModelTypes, dataType, schema);
        }
    }

}
