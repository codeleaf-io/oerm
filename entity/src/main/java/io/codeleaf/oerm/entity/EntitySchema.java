package io.codeleaf.oerm.entity;

import io.codeleaf.modeling.data.RecordType;
import io.codeleaf.modeling.data.ValueType;

import java.util.Map;
import java.util.Objects;

public final class EntitySchema {

    public static final String DEFAULT_VERSION = "1";

    private final String dataType;
    private final String version;
    private final RecordType recordType;
    private final RecordType declaredType;
    private final EntitySchema parentSchema;

    private EntitySchema(String dataType, String version, RecordType recordType, RecordType declaredType, EntitySchema parentSchema) {
        this.dataType = dataType;
        this.version = version;
        this.recordType = recordType;
        this.declaredType = declaredType;
        this.parentSchema = parentSchema;
    }

    public String getDataType() {
        return dataType;
    }

    public String getVersion() {
        return version;
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

    public static EntitySchema create(String dataType, RecordType recordType) {
        return create(dataType, recordType, null);
    }

    public static EntitySchema create(String dataType, RecordType declaredType, EntitySchema parentSchema) {
        return create(dataType, DEFAULT_VERSION, declaredType, parentSchema);
    }

    public static EntitySchema create(String dataType, String version, RecordType declaredType, EntitySchema parentSchema) {
        Objects.requireNonNull(dataType);
        if (dataType.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Objects.requireNonNull(version);
        if (version.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Objects.requireNonNull(declaredType);
        RecordType recordType;
        if (parentSchema == null) {
            recordType = declaredType;
        } else {
            RecordType.Builder builder = new RecordType.Builder();
            addAllFields(builder, declaredType);
            addAllFields(builder, parentSchema.getRecordType());
            recordType = builder.build();
        }
        return new EntitySchema(dataType, version, recordType, declaredType, parentSchema);
    }

    private static void addAllFields(RecordType.Builder builder, RecordType recordType) {
        for (Map.Entry<String, ValueType> entry : recordType.getFieldTypes().entrySet()) {
            builder.withField(entry.getKey(), entry.getValue(), recordType.isRequiredField(entry.getKey()));
        }
    }
}