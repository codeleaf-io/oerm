package io.codeleaf.oerm.mapper.object;

import io.codeleaf.common.utils.Methods;
import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.data.RecordType;
import io.codeleaf.oerm.entity.EntitySchema;
import io.codeleaf.oerm.mapper.entity.EntitySchemaProvider;
import io.codeleaf.oerm.mapper.entity.EntitySchemaFactory;
import io.codeleaf.oerm.object.Entity;
import io.codeleaf.oerm.object.mapping.Ignored;
import io.codeleaf.oerm.object.mapping.Mapping;
import io.codeleaf.oerm.object.mapping.Optional;

import java.lang.reflect.Method;
import java.util.Objects;

public final class ObjectEntitySchemaFactory implements EntitySchemaFactory<Class<? extends Entity>> {

    private final ObjectFieldMapperProvider mapperProvider;
    private final EntitySchemaProvider entitySchemaProvider;

    public ObjectEntitySchemaFactory(ObjectFieldMapperProvider mapperProvider, EntitySchemaProvider entitySchemaProvider) {
        this.mapperProvider = mapperProvider;
        this.entitySchemaProvider = entitySchemaProvider;
    }

    @Override
    public EntitySchema createEntitySchema(Class<? extends Entity> entityType) {
        Objects.requireNonNull(entityType);
        if (!Entity.class.isAssignableFrom(entityType)) {
            throw new IllegalArgumentException();
        }
        String dataType = EntityTypes.determineDataType(entityType);
        RecordType.Builder builder = new RecordType.Builder();
        for (Method method : entityType.getMethods()) {
            if (!Methods.isSupplier(method) || Methods.hasAnnotation(method, Ignored.class)) {
                continue;
            }
            Mapping mapping = method.getAnnotation(Mapping.class);
            String entityFieldName = determineFieldName(mapping, method.getName());
            builder.withField(
                    entityFieldName,
                    mapperProvider.get(mapping).getType(entityType, method, entityFieldName),
                    !Methods.hasAnnotation(method, Optional.class));
        }
        EntitySchema entitySchema;
        if (!entityType.equals(Entity.class)) {
            entitySchema = EntitySchema.create(
                    dataType,
                    builder.build(),
                    getParentSchema(Types.cast(entityType.getSuperclass())));
        } else {
            entitySchema = EntitySchema.create(dataType, builder.build());
        }
        return entitySchema;
    }

    private EntitySchema getParentSchema(Class<? extends Entity> entityType) {
        String dataType = EntityTypes.determineDataType(entityType);
        return entitySchemaProvider.hasSchema(dataType) ? entitySchemaProvider.getSchema(dataType) : createEntitySchema(entityType);
    }

    private static String determineFieldName(Mapping mapping, String name) {
        return mapping == null || mapping.value().isEmpty() ? name : mapping.value();
    }
}
