package io.codeleaf.oerm.dal.impl;

import io.codeleaf.oerm.entity.EntitySchema;

import java.util.HashMap;
import java.util.Map;

public final class EntitySchemaRegistry {

    private final Map<String, EntitySchema> entitySchemas = new HashMap<>();

    public void register(EntitySchema entitySchema) {
        entitySchemas.put(entitySchema.getDataType(), entitySchema);
    }

    public boolean contains(String name) {
        return entitySchemas.containsKey(name);
    }

    public EntitySchema lookup(String name) {
        return entitySchemas.get(name);
    }
}
