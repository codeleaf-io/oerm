package io.codeleaf.oerm.mapper.object;

import io.codeleaf.common.utils.Types;
import io.codeleaf.oerm.object.Entity;
import io.codeleaf.oerm.object.Reference;
import io.codeleaf.oerm.object.Tenant;
import io.codeleaf.oerm.object.mapping.ObjectMeta;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

// TODO: complete this
public final class ObjectMetaParser<E extends Entity> {

    private final Class<E> entityType;

    public ObjectMetaParser(Class<E> entityType) {
        this.entityType = entityType;
    }

    public Class<E> getEntityType() {
        return entityType;
    }

    public Entity.Meta parseObjectMeta(Map<Method, Object> fields) {
        return new ObjectMeta(
                parseUUID(fields),
                entityType,
                parseDataSubjects(fields),
                null, //parseLegalOwner(entityType, fields),
                null, //parseDataSteward(entityType, fields),
                null); //parsePartition(entityType, fields));
    }

    private Set<Reference<Tenant>> parseDataSubjects(Map<Method, Object> fields) {
        return null;
    }

    private UUID parseUUID(Map<Method, Object> fields) {
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

    private ObjectMeta.UUID.Mapper<?> getUUIDMapper(Class<? extends ObjectMeta.UUID.Mapper<?>> uuidMapperType) {
        return null;
    }

    private ObjectMeta.UUID.Generator getUUIDGenerator(Class<? extends ObjectMeta.UUID.Generator> uuidGeneratorType) {
        return null;
    }
}
