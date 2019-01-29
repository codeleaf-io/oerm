package io.codeleaf.oerm.entity;

import io.codeleaf.modeling.data.RecordType;
import io.codeleaf.modeling.data.ValueType;

import java.util.Map;
import java.util.Objects;

public final class EntitySchema {

    private final String dataType;
    private final RecordType recordType;
    private final RecordType declaredType;
    private final EntitySchema parentSchema;

    private EntitySchema(String dataType, RecordType recordType, RecordType declaredType, EntitySchema parentSchema) {
        this.dataType = dataType;
        this.recordType = recordType;
        this.declaredType = declaredType;
        this.parentSchema = parentSchema;
    }

    public String getDataType() {
        return dataType;
    }

    public RecordType getRecordType() {
        return recordType;
    }

    public RecordType getDeclaredType() {
        return declaredType;
    }

    public EntitySchema getParentSchema() {
        return parentSchema;
    }

    public static EntitySchema create(String dataType, RecordType declaredType, EntitySchema parentSchema) {
        Objects.requireNonNull(dataType);
        if (dataType.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Objects.requireNonNull(declaredType);
        Objects.requireNonNull(parentSchema);
        RecordType.Builder builder = new RecordType.Builder();
        addAllFields(builder, declaredType);
        addAllFields(builder, parentSchema.getRecordType());
        return new EntitySchema(dataType, builder.build(), declaredType, parentSchema);
    }

    private static void addAllFields(RecordType.Builder builder, RecordType recordType) {
        for (Map.Entry<String, ValueType> entry : recordType.getFieldTypes().entrySet()) {
            builder.withField(entry.getKey(), entry.getValue(), recordType.isRequiredField(entry.getKey()));
        }
    }

    public static EntitySchema create(String dataType, RecordType recordType) {
        Objects.requireNonNull(dataType);
        if (dataType.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Objects.requireNonNull(recordType);
        return new EntitySchema(dataType, recordType, recordType, null);
    }
}