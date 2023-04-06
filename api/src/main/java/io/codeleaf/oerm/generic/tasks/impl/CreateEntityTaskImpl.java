package io.codeleaf.oerm.generic.tasks.impl;

import io.codeleaf.oerm.generic.tasks.CreateEntityTask;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public final class CreateEntityTaskImpl<K, D, F, V> extends AbstractAddTask<K, D> implements CreateEntityTask<K, D, F, V> {

    private final Class<F> fieldNameType;
    private final Class<V> fieldValueType;
    private final Map<F, V> fields;

    private CreateEntityTaskImpl(Class<F> fieldNameType, Class<V> fieldValueType, D dataType, Map<F, V> fields, Class<K> entityIdType) {
        super(entityIdType, dataType);
        this.fieldNameType = fieldNameType;
        this.fieldValueType = fieldValueType;
        this.fields = fields;
    }

    @Override
    public Class<F> getFieldNameType() {
        return fieldNameType;
    }

    @Override
    public Class<V> getFieldValueType() {
        return fieldValueType;
    }

    @Override
    public Map<F, V> getFields() {
        return fields;
    }

    public static final class Builder<K, D, F, V>
            extends AbstractAddTask.Builder<Builder<K, D, F, V>, CreateEntityTaskImpl<K, D, F, V>, K, D>
            implements CreateEntityTask.Builder<Builder<K, D, F, V>, CreateEntityTaskImpl<K, D, F, V>, K, D, F, V> {

        private final Map<F, V> fields = new LinkedHashMap<>();
        private final Class<F> fieldNameType;
        private final Class<V> fieldValueType;

        public Builder(Class<K> entityIdType, D dataType, Class<F> fieldNameType, Class<V> fieldValueType) {
            super(entityIdType, dataType);
            this.fieldNameType = fieldNameType;
            this.fieldValueType = fieldValueType;
        }

        @Override
        public Builder<K, D, F, V> withField(F fieldName, V fieldValue) {
            Objects.requireNonNull(fieldName);
            fields.put(fieldName, fieldValue);
            return this;
        }

        @Override
        protected void validate() {
            super.validate();
            if (fieldNameType == null || fieldValueType == null) {
                throw new IllegalStateException();
            }
            if (fields.isEmpty()) {
                throw new IllegalStateException("No fields set!");
            }
        }

        @Override
        public CreateEntityTaskImpl<K, D, F, V> build() {
            validate();
            return new CreateEntityTaskImpl<>(fieldNameType, fieldValueType, dataType,
                    Collections.unmodifiableMap(new LinkedHashMap<>(fields)),
                    entityIdType);
        }
    }
}
