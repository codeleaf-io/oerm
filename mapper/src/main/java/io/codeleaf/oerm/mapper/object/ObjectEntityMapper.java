package io.codeleaf.oerm.mapper.object;

import io.codeleaf.common.utils.MethodReferences;
import io.codeleaf.common.utils.Methods;
import io.codeleaf.modeling.data.RecordWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.entity.EntityRecord;
import io.codeleaf.oerm.entity.EntitySchema;
import io.codeleaf.oerm.mapper.entity.EntityMapper;
import io.codeleaf.oerm.object.Entity;
import io.codeleaf.oerm.object.mapping.ObjectFieldGenerator;
import io.codeleaf.oerm.object.mapping.ObjectFieldMapper;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public final class ObjectEntityMapper<E extends Entity> implements EntityMapper<E, Supplier<?>, Object> {

    private final Class<E> entityType;
    private final String dataType;
    private final Method[] nonIgnoredSuppliers;
    private final Method[] nonGeneratedRequiredSuppliers;
    private final Map<Method, ObjectFieldGenerator> applicationGenerators;
    private final Set<Method> optionalApplicationGenerators;
    private final Map<Method, String> fieldNameMapping;
    private final Map<String, Method> reversedFieldNameMapping;
    private final Map<Method, ObjectFieldMapper> fieldMappers;
    private final ObjectMetaParser<E> objectMetaParser;
    private final ObjectMetaMapper objectMetaMapper;
    private final EntitySchema entitySchema;

    public ObjectEntityMapper(
            Class<E> entityType,
            String dataType, Method[] nonIgnoredSuppliers,
            Method[] nonGeneratedRequiredSuppliers,
            Map<Method, ObjectFieldGenerator> applicationGenerators,
            Set<Method> optionalApplicationGenerators,
            Map<Method, String> fieldNameMapping,
            Map<String, Method> reversedFieldNameMapping,
            Map<Method, ObjectFieldMapper> fieldMappers,
            ObjectMetaParser<E> objectMetaParser,
            ObjectMetaMapper objectMetaMapper,
            EntitySchema entitySchema) {
        this.entityType = entityType;
        this.dataType = dataType;
        this.nonIgnoredSuppliers = nonIgnoredSuppliers;
        this.nonGeneratedRequiredSuppliers = nonGeneratedRequiredSuppliers;
        this.applicationGenerators = applicationGenerators;
        this.optionalApplicationGenerators = optionalApplicationGenerators;
        this.fieldNameMapping = fieldNameMapping;
        this.reversedFieldNameMapping = reversedFieldNameMapping;
        this.fieldMappers = fieldMappers;
        this.objectMetaParser = objectMetaParser;
        this.objectMetaMapper = objectMetaMapper;
        this.entitySchema = entitySchema;
    }

    public Class<E> getEntityType() {
        return entityType;
    }

    @Override
    public String getDataType() {
        return dataType;
    }

    public EntityRecord mapObject(E entity) {
        Map<Method, Object> methodFields = new LinkedHashMap<>();
        for (Method method : nonIgnoredSuppliers) {
            methodFields.put(method, Methods.invoke(method, entity));
        }
        RecordWithType recordWithType = RecordWithType.create(mapFromMethodFields(methodFields), entitySchema.getRecordType());
        Entity.Meta objectMeta = entity.getMeta() == null ? objectMetaParser.parseObjectMeta(methodFields) : entity.getMeta();
        EntityRecord.Meta entityMeta = objectMetaMapper.mapObjectMeta(objectMeta);
        return EntityRecord.create(recordWithType, entityMeta);
    }

    // TODO: check if this is correct for hashCode, equals and toString...
    @SuppressWarnings("unchecked")
    @Override
    public E mapEntity(EntityRecord entityRecord) {
        Entity.Meta objectMeta = objectMetaMapper.mapEntityMeta(entityRecord.getMeta());
        Map<Method, Object> entityFields = mapToMethodFields(entityRecord.getRecord().getValue());
        return (E) Proxy.newProxyInstance(entityType.getClassLoader(), new Class<?>[]{entityType},
                (proxy, method, args) -> entityFields.containsKey(method)
                        ? entityFields.get(method)
                        : method.getName().equals("getMeta") && args.length == 0
                        ? objectMeta
                        : Methods.invoke(method, this));
    }

    public Map<String, ValueWithType<?>> mapObjectFields(Map<Supplier<?>, Object> objectFields) {
        Map<Method, Object> methodFields = new LinkedHashMap<>();
        for (Map.Entry<Supplier<?>, Object> entry : objectFields.entrySet()) {
            methodFields.put(MethodReferences.derefence(entry.getKey()), entry.getValue());
        }
        return mapFromMethodFields(methodFields);
    }

    @Override
    public Map<Supplier<?>, Object> mapEntityFields(Map<String, ValueWithType<?>> entityFields) {
        Map<Method, Object> methodFields = mapToMethodFields(entityFields);
        Map<Supplier<?>, Object> objectFields = new LinkedHashMap<>();
        for (Map.Entry<Method, Object> entry : methodFields.entrySet()) {
            objectFields.put(MethodReferences.reference(entry.getKey()), entry.getValue());
        }
        return objectFields;
    }

    private Map<Method, Object> mapToMethodFields(Map<String, ValueWithType<?>> entityFields) {
        Map<Method, Object> methodFields = new LinkedHashMap<>();
        for (Map.Entry<String, ValueWithType<?>> entry : entityFields.entrySet()) {
            Method method = reversedFieldNameMapping.get(entry.getKey());
            ObjectFieldMapper fieldMapper = fieldMappers.get(method);
            Object objectFieldValue = fieldMapper.mapEntityField(entityType, method, entry.getKey(), entityFields);
            methodFields.put(method, objectFieldValue);
        }
        return methodFields;
    }

    private Map<String, ValueWithType<?>> mapFromMethodFields(Map<Method, Object> methodFields) {
        ensureNonGeneratedRequiredFieldsAreSet(methodFields);
        generateFieldValues(methodFields);
        return mapToEntityFields(methodFields);
    }

    private Map<String, ValueWithType<?>> mapToEntityFields(Map<Method, Object> objectFields) {
        Map<String, ValueWithType<?>> entityFields = new LinkedHashMap<>();
        for (Map.Entry<Method, Object> entry : objectFields.entrySet()) {
            String fieldName = fieldNameMapping.get(entry.getKey());
            ObjectFieldMapper fieldMapper = fieldMappers.get(entry.getKey());
            ValueWithType<?> entityFieldValue = fieldMapper.mapObjectField(entityType, entry.getKey(), fieldName, objectFields);
            entityFields.put(fieldName, entityFieldValue);
        }
        return entityFields;
    }

    private void ensureNonGeneratedRequiredFieldsAreSet(Map<Method, Object> fields) {
        for (Method method : nonGeneratedRequiredSuppliers) {
            Object providedValue = fields.get(method);
            if (providedValue == null) {
                throw new IllegalArgumentException("Required field not set: " + method.getName());
            }
        }
    }

    private void generateFieldValues(Map<Method, Object> fields) {
        for (Map.Entry<Method, ObjectFieldGenerator> entry : applicationGenerators.entrySet()) {
            Object providedValue = fields.get(entry.getKey());
            if (providedValue != null) {
                continue;
            }
            Object generatedValue = entry.getValue().generate(entityType, entry.getKey(), fields);
            if (generatedValue != null) {
                fields.put(entry.getKey(), generatedValue);
            } else if (!optionalApplicationGenerators.contains(entry.getKey())) {
                throw new IllegalArgumentException("Generator generated null value: " + entry.getKey().getName());
            }
        }
    }
}
