package io.codeleaf.oerm.dal.impl;

import io.codeleaf.oerm.dal.DataTypeRegistry;
import io.codeleaf.oerm.entity.EntitySchema;
import io.codeleaf.oerm.entity.EntitySchemaProvider;
import io.codeleaf.oerm.object.Entity;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public final class DataTypeMapper implements DataTypeRegistry {

    private final Map<String, EntitySchema> entitySchemas = new LinkedHashMap<>();

    public Class<? extends Entity> map(String dataType) {
        return null;
    }

    public String map(Class<? extends Entity> entityType) {
        return null;
    }

    @Override
    public void register(Class<? extends Entity> objectType) {
        Objects.requireNonNull(objectType);
        // TODO: implement mapping from objectType to EntitySchema

    }

    @Override
    public void register(EntitySchema entitySchema) {
        entitySchemas.put(entitySchema.getDataType(), entitySchema);
    }

    @Override
    public Iterable<EntitySchema> listEntityTypes() {
        return null;
    }

    @Override
    public EntitySchema lookup(String dataType) {
        return null;
    }

    @Override
    public boolean isPresent(Class<? extends Entity> objectType) {
        return false;
    }

    @Override
    public boolean isPresent(String dataType) {
        return false;
    }

    @Override
    public boolean isBound(Class<? extends Entity> objectType) {
        return false;
    }

    @Override
    public boolean isBound(String dataType) {
        return false;
    }
}
