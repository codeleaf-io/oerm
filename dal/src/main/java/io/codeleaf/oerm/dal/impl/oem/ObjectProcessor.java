package io.codeleaf.oerm.dal.impl.oem;

import io.codeleaf.common.utils.MethodReferences;
import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.data.RecordType;
import io.codeleaf.modeling.data.RecordWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.entity.EntityRecord;
import io.codeleaf.oerm.entity.EntitySchema;
import io.codeleaf.oerm.object.Entity;
import io.codeleaf.oerm.object.Reference;
import io.codeleaf.oerm.object.Tenant;
import io.codeleaf.oerm.object.mapping.Optional;
import io.codeleaf.oerm.object.mapping.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.function.Supplier;

public class ObjectProcessor {

    public static class Methods {

        private Methods() {
        }

        public static Object invoke(Method method, Object object) {
            try {
                return method.invoke(object);
            } catch (IllegalAccessException | InvocationTargetException cause) {
                throw new IllegalArgumentException(cause);
            }
        }

        public static <T extends Annotation> boolean hasAnnotation(Method method, Class<T> annotationClass) {
            return method.getAnnotation(annotationClass) != null;
        }
    }

    public <E extends Entity> Map<Method, Object> processObject(Class<E> entityType, E entity) {
        Map<Method, Object> methodFields = new HashMap<>();
        for (Method method : entityType.getMethods()) {
            if (isSupplier(method) && !Methods.hasAnnotation(method, Ignored.class)) {
                methodFields.put(method, Methods.invoke(method, entity));
            }
        }
        return methodFields;
    }

    private static boolean isSupplier(Method method) {
        return method.getParameterTypes().length == 0 && !method.getReturnType().equals(Void.class);
    }

    public <E extends Entity> Map<Method, Object> processFields(Class<E> entityType, Map<Supplier<?>, Object> fields) {
        Map<Method, Object> methodFields = new HashMap<>();
        for (Map.Entry<Supplier<?>, Object> entry : fields.entrySet()) {
            methodFields.put(MethodReferences.derefence(entry.getKey()), entry.getValue());
        }
        return methodFields;
    }

    public <E extends Entity> void ensureNonGeneratedRequiredFieldsAreSet(Class<E> entityType, Map<Method, Object> fields) {
        for (Method method : entityType.getMethods()) {
            Object providedValue = fields.get(method);
            if (providedValue != null || Methods.hasAnnotation(method, Optional.class) || Methods.hasAnnotation(method, Generated.class)) {
                continue;
            }
            throw new IllegalArgumentException("Required field not set: " + method.getName());
        }
    }

    public <E extends Entity> Map<Method, Object> generateFieldValues(Class<E> entityType, Map<Method, Object> fields) {
        for (Method method : entityType.getMethods()) {
            Object providedValue = fields.get(method);
            if (providedValue != null || !Methods.hasAnnotation(method, Generated.class)) {
                continue;
            }
            Generated generated = method.getAnnotation(Generated.class);
            if (generated.value() == Locality.APPLICATION) {
                continue;
            }
            FieldValueGenerator generator = getGenerator(generated.generator());
            if (!generator.supportsType(method.getReturnType())) {
                throw new IllegalStateException("Generator on field " + method.getName() + " does not support type: " + method.getReturnType().getCanonicalName());
            }
            Object generatedValue = generator.generate(entityType, method, fields);
            if (generatedValue == null && !Methods.hasAnnotation(method, Optional.class)) {
                throw new IllegalArgumentException("Field " + method.getName() + " must be set, but generator returned null!");
            } else {
                fields.put(method, generatedValue);
            }
        }
        return fields;
    }

    private FieldValueGenerator getGenerator(Class<? extends FieldValueGenerator> generatorType) {
        return null;
    }

    public <E extends Entity> Entity.Meta generateEntityMeta(Class<E> entityType, Map<Method, Object> fields) {
        return new ObjectMetaImpl(
                parseUUID(entityType, fields),
                entityType,
                parseDataSubjects(entityType, fields),
                null, //parseLegalOwner(entityType, fields),
                null, //parseDataSteward(entityType, fields),
                null); //parsePartition(entityType, fields));
    }

    private <E extends Entity> Set<Reference<Tenant>> parseDataSubjects(Class<E> entityType, Map<Method, Object> fields) {
        return null;
    }

    private <E extends Entity> UUID parseUUID(Class<E> entityType, Map<Method, Object> fields) {
        UUID uuid = null;
        int found = 0;
        ObjectMeta.UUID classDefinition = entityType.getAnnotation(ObjectMeta.UUID.class);
        if (classDefinition != null) {
            found++;
        }
        for (Method method : fields.keySet()) {
            ObjectMeta.UUID methodDefinition = method.getAnnotation(ObjectMeta.UUID.class);
            if (methodDefinition != null) {
                if (found > 0) {
                    throw new IllegalArgumentException("Can't have 2 UUID annotations!");
                }
                found++;
                ObjectMeta.UUID.Mapper<?> mapper = getUUIDMapper(methodDefinition.mapper());
                if (mapper.getFieldType() == null || !mapper.getFieldType().isAssignableFrom(method.getReturnType())) {
                    throw new IllegalStateException("UUID Mapper can't parse field: " + method.getName());
                }
                uuid = mapper.generate(entityType, method, Types.cast(fields.get(method)));
            }
        }
        if (found == 1 && classDefinition != null) {
            uuid = getUUIDGenerator(classDefinition.generator()).generate(entityType, fields);
        }
        return uuid;
    }

    private final ObjectMeta.UUID.Mapper<?> getUUIDMapper(Class<? extends ObjectMeta.UUID.Mapper<?>> uuidMapperType) {
        return null;
    }

    private final ObjectMeta.UUID.Generator getUUIDGenerator(Class<? extends ObjectMeta.UUID.Generator> uuidGeneratorType) {
        return null;
    }

    public <E extends Entity> EntityRecord mapFieldsToEntity(Class<E> entityType, Map<Method, Object> fields, Entity.Meta objectMeta) {
        EntitySchema entitySchema = getEntitySchema(entityType);
        RecordWithType.Builder builder = new RecordWithType.Builder(entitySchema.getRecordType());
        for (Method method : fields.keySet()) {
            Mapping mapping = method.getAnnotation(Mapping.class);
            String entityFieldName = determineFieldName(mapping, method.getName());
            ValueWithType<?> valueWithType = getMapper(mapping).map(entityType, method, entityFieldName, fields);
            builder.withField(entityFieldName, valueWithType);
        }
        return EntityRecord.create(builder.build(), mapObjectMeta(objectMeta));
    }

    private EntityFieldMapper getMapper(Mapping mapping) {
        return null;
    }

    private String determineFieldName(Mapping mapping, String name) {
        return mapping == null || mapping.value().isEmpty() ? name : mapping.value();
    }

    private EntityRecord.Meta mapObjectMeta(Entity.Meta objectMeta) {
        return new MetaFactory(null).createEntityMeta(objectMeta);
    }

    private <E extends Entity> EntitySchema getEntitySchema(Class<E> entityType) {
        return null;
    }

    // TODO: make sure we cache results...
    public EntitySchema defineEntitySchema(Class<? extends Entity> entityType) {
        return doDefineEntitySchema(entityType);
    }

    public EntitySchema doDefineEntitySchema(Class<? extends Entity> entityType) {
        Objects.requireNonNull(entityType);
        if (!Entity.class.isAssignableFrom(entityType)) {
            throw new IllegalArgumentException();
        }
        String dataType = determineDataType(entityType);
        RecordType.Builder builder = new RecordType.Builder();
        for (Method method : entityType.getMethods()) {
            if (!isSupplier(method) || Methods.hasAnnotation(method, Ignored.class)) {
                continue;
            }
            Mapping mapping = method.getAnnotation(Mapping.class);
            String entityFieldName = determineFieldName(mapping, method.getName());
            builder.withField(
                    entityFieldName,
                    getMapper(mapping).getType(entityType, method, entityFieldName),
                    !Methods.hasAnnotation(method, Optional.class));
        }
        EntitySchema entitySchema;
        if (!entityType.equals(Entity.class)) {
            entitySchema = EntitySchema.create(
                    dataType,
                    builder.build(),
                    defineEntitySchema(Types.cast(entityType.getSuperclass())));
        } else {
            entitySchema = EntitySchema.create(dataType, builder.build());
        }
        return entitySchema;
    }

    private String determineDataType(Class<? extends Entity> entityType) {
        ObjectMeta.DataType dataType = entityType.getAnnotation(ObjectMeta.DataType.class);
        String entityDataType;
        if (dataType == null || dataType.value().isEmpty()) {
            entityDataType = entityType.getCanonicalName();
        } else {
            entityDataType = dataType.value();
        }
        if (dataType == null || dataType.version().isEmpty()) {
            Long serialVersionUID = getSerialVersionUID(entityType);
            if (serialVersionUID == null) {
                entityDataType += ":unversioned";
            } else {
                entityDataType += ":" + serialVersionUID;
            }
        } else {
            entityDataType += ":" + dataType.version();
        }
        return entityDataType;
    }

    private Long getSerialVersionUID(Class<? extends Entity> entityType) {
        for (Field field : entityType.getDeclaredFields()) {
            if (field.getName().equals("serialVersionUID")) {
                if (Modifier.isStatic(field.getModifiers()) && field.getType().equals(Long.class)) {
                    try {
                        return field.getLong(null);
                    } catch (IllegalAccessException e) {
                        return null;
                    }
                } else {
                    break;
                }
            }
        }
        return null;
    }

    /*
    public <E extends Entity> E processEntity(Class<E> entityType, E entity) {
        Map<String, Object> values = new HashMap<>();
        for (Method method : entityType.getMethods()) {
            if (isSupplier(method)) {
                values.put(method.getName(), getValue(method, entity));
            }
        }
        return doToObject(entityType, values);
    }

    public <E extends Entity> boolean isValid(Class<E> entityType, E entity) {
    }

    private <E extends Entity> E doToObject(Class<E> entityType, Map<String, Object> fields) {
        return Types.cast(Proxy.newProxyInstance(entityType.getClassLoader(), new Class<?>[]{entityType},
                (Object proxy, Method method, Object[] args) -> fields.get(method.getName())));
    }

    public <E extends Entity> Map<Supplier<?>, Object> processFields(Class<E> entityType, Map<Supplier<?>, Object> fields) {

    }

    public <E extends Entity> boolean isValid(Class<E> entityType, Map<Supplier<?>, Object> fields) {

    }

    public <E extends Entity> EntityRecord toEntity(Class<E> entityType, E entity) {

    }

    public <E extends Entity> Map<String, ValueWithType<?>> toEntity(Class<E> entityType, Map<Supplier<?>, Object> fields) {

    }

    public <E extends Entity> EntitySchema toEntity(Class<E> entityType) {

    }
*/

}
