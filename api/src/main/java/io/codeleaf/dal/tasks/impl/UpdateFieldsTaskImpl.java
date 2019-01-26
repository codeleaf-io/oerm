package io.codeleaf.dal.tasks.impl;

import io.codeleaf.dal.tasks.UpdateFieldsTask;
import io.codeleaf.modeling.selection.Selection;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public final class UpdateFieldsTaskImpl<D, F, V> extends AbstractSelectTask<D, F, V, Void> implements UpdateFieldsTask<D, F, V, Void> {

    private final Map<F, V> fields;

    private UpdateFieldsTaskImpl(Selection selection, Class<F> fieldNameType, Class<V> fieldValueType, D dataType, Map<F, V> fields) {
        super(selection, fieldNameType, fieldValueType, dataType, Void.class);
        this.fields = fields;
    }

    @Override
    public Map<F, V> getFields() {
        return fields;
    }

    public static final class Builder<D, F, V>
            extends AbstractSelectTask.Builder<Builder<D, F, V>, UpdateFieldsTaskImpl<D, F, V>, D, F, V, Void>
            implements UpdateFieldsTask.Builder<Builder<D, F, V>, UpdateFieldsTaskImpl<D, F, V>, D, F, V, Void> {

        private final Map<F, V> fields = new LinkedHashMap<>();

        public Builder() {
        }

        public Builder(D dataType, Class<F> fieldNameType, Class<V> fieldValueType) {
            super(dataType, fieldNameType, fieldValueType);
        }

        @Override
        public Builder<D, F, V> withField(F fieldName, V fieldValue) {
            Objects.requireNonNull(fieldName);
            fields.put(fieldName, fieldValue);
            return this;
        }

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
