package io.codeleaf.oerm.tasks.impl;

import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.Count;
import io.codeleaf.oerm.tasks.UpdateFieldsTask;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public final class UpdateFieldsTaskImpl<D, F, V> extends AbstractSelectTask<D, F, V, Count> implements UpdateFieldsTask<D, F, V, Count> {

    private final Map<F, V> fields;

    private UpdateFieldsTaskImpl(Selection selection, Class<F> fieldNameType, Class<V> fieldValueType, D dataType, Map<F, V> fields) {
        super(selection, fieldNameType, fieldValueType, dataType, Count.class);
        this.fields = fields;
    }

    @Override
    public Map<F, V> getFields() {
        return fields;
    }

    public static final class Builder<D, F, V>
            extends AbstractSelectTask.Builder<Builder<D, F, V>, UpdateFieldsTaskImpl<D, F, V>, D, F, V, Count>
            implements UpdateFieldsTask.Builder<Builder<D, F, V>, UpdateFieldsTaskImpl<D, F, V>, D, F, V, Count> {

        private final Map<F, V> fields = new LinkedHashMap<>();

        public Builder(D dataType, Class<F> fieldNameType, Class<V> fieldValueType) {
            super(dataType, fieldNameType, fieldValueType);
        }

        @Override
        public Builder<D, F, V> withField(F fieldName, V fieldValue) {
            Objects.requireNonNull(fieldName);
            fields.put(fieldName, fieldValue);
            return this;
        }

        @Override
        protected void validate() {
            super.validate();
            if (fields.isEmpty()) {
                throw new IllegalStateException("No fields set!");
            }
        }

        @Override
        public UpdateFieldsTaskImpl<D, F, V> build() {
            validate();
            return new UpdateFieldsTaskImpl<>(selection, fieldNameType, fieldValueType, dataType,
                    Collections.unmodifiableMap(new LinkedHashMap<>(fields)));
        }
    }
}
