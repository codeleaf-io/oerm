package io.codeleaf.oerm.impl.tasks.data;

import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.impl.tasks.AbstractTypeBasedTask;
import io.codeleaf.oerm.tasks.data.CreateEntityTask;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class GenericCreateEntityTask<E, K, D, F, V, S>
        extends AbstractTypeBasedTask<E, K, D, F, V, S, K>
        implements CreateEntityTask<E, K, D, F, V, S> {

    private final Map<F, V> fields;

    public GenericCreateEntityTask(DataModelTypes<E, K, D, F, V, S> dataModelTypes, D dataType, Map<F, V> fields) {
        super(dataModelTypes, dataModelTypes.getEntityIdType(), dataType);
        this.fields = fields;
    }

    @Override
    public Map<F, V> getFields() {
        return fields;
    }

    public static class Builder<E, K, D, F, V, S>
            extends AbstractTypeBasedTask.Builder
            <
                    Builder<E, K, D, F, V, S>,
                    GenericCreateEntityTask<E, K, D, F, V, S>,
                    E, K, D, F, V, S, K>
            implements CreateEntityTask.Builder
            <
                    Builder<E, K, D, F, V, S>,
                    GenericCreateEntityTask<E, K, D, F, V, S>,
                    E, K, D, F, V, S> {

        protected final Map<F, V> fields = new LinkedHashMap<>();

        public Builder(DataModelTypes<E, K, D, F, V, S> dataModelTypes) {
            super(dataModelTypes);
        }

        @Override
        public Builder<E, K, D, F, V, S> withField(F fieldName, V fieldValue) {
            Objects.requireNonNull(fieldName);
            fields.put(fieldName, fieldValue);
            return this;
        }

        @Override
        public GenericCreateEntityTask<E, K, D, F, V, S> build() {
            validate();
            return new GenericCreateEntityTask<>(dataModelTypes, dataType, Collections.unmodifiableMap(new LinkedHashMap<>(fields)));
        }
    }
}
