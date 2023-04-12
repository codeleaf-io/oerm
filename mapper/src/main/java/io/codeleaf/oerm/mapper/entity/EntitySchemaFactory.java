package io.codeleaf.oerm.mapper.entity;

import io.codeleaf.oerm.entity.EntitySchema;

public interface EntitySchemaFactory<T> {

    EntitySchema createEntitySchema(T definition);
}
