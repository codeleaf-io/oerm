package io.codeleaf.oerm.impl.tasks.data;

import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.Count;
import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.impl.tasks.AbstractSelectTask;
import io.codeleaf.oerm.tasks.data.UpdateFieldsTask;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class GenericUpdateFieldsTask<E, K, D, F, V, S> extends AbstractSelectTask<E, K, D, F, V, S, Count> implements UpdateFieldsTask<E, K, D, F, V, S> {

    private final Map<F, V> fields;

    public GenericUpdateFieldsTask(DataModelTypes<E, K, D, F, V, S> dataModelTypes, D dataType, Selection selection, Map<F, V> fields) {
        super(dataModelTypes, Count.class, dataType, selection);
        this.fields = fields;
    }

    @Override
    public Map<F, V> getFields() {
        return fields;
    }

    public static class Builder<E, K, D, F, V, S> extends AbstractSelectTask.Builder<Builder<E, K, D, F, V, S>, GenericUpdateFieldsTask<E, K, D, F, V, S>, E, K, D, F, V, S, Count> implements UpdateFieldsTask.Builder<Builder<E, K, D, F, V, S>, GenericUpdateFieldsTask<E, K, D, F, V, S>, E, K, D, F, V, S> {

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
        public GenericUpdateFieldsTask<E, K, D, F, V, S> build() {
            validate();
            return new GenericUpdateFieldsTask<>(dataModelTypes, dataType, selection, Collections.unmodifiableMap(new LinkedHashMap<>(fields)));
        }
    }
}
