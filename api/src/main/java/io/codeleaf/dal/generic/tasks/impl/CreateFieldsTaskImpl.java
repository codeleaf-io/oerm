package io.codeleaf.dal.generic.tasks.impl;

import io.codeleaf.dal.generic.tasks.CreateFieldsTask;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public final class CreateFieldsTaskImpl<K, D, F, V> extends AbstractCreateTask<K, D> implements CreateFieldsTask<K, D, F, V> {

    private final Class<F> fieldNameType;
    private final Class<V> fieldValueType;
    private final Map<F, V> fields;

    private CreateFieldsTaskImpl(Class<F> fieldNameType, Class<V> fieldValueType, D dataType, Map<F, V> fields, Class<K> objectIdType) {
        super(objectIdType, dataType);
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
            extends AbstractCreateTask.Builder<Builder<K, D, F, V>, CreateFieldsTaskImpl<K, D, F, V>, K, D>
            implements CreateFieldsTask.Builder<Builder<K, D, F, V>, CreateFieldsTaskImpl<K, D, F, V>, K, D, F, V> {

        private final Map<F, V> fields = new LinkedHashMap<>();
        private Class<F> fieldNameType;
        private Class<V> fieldValueType;

        public Builder() {
        }

        public Builder(Class<K> objectIdType, D dataType, Class<F> fieldNameType, Class<V> fieldValueType) {
            super(objectIdType, dataType);
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
        public Builder<K, D, F, V> withFieldNameType(Class<F> fieldNameType) {
            Objects.requireNonNull(fieldNameType);
            this.fieldNameType = fieldNameType;
            return this;
        }

        @Override
        public Builder<K, D, F, V> withFieldValueType(Class<V> fieldValueType) {
            Objects.requireNonNull(fieldValueType);
            this.fieldValueType = fieldValueType;
            return this;
        }

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
        public CreateFieldsTaskImpl<K, D, F, V> build() {
            validate();
            return new CreateFieldsTaskImpl<>(fieldNameType, fieldValueType, dataType,
                    Collections.unmodifiableMap(new LinkedHashMap<>(fields)),
                    objectIdType);
        }
    }
}
