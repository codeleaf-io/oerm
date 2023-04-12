package io.codeleaf.oerm.mapper.entity;

import io.codeleaf.oerm.entity.EntitySchema;

public interface EntitySchemaProvider {

    boolean hasSchema(String dataType);

    EntitySchema getSchema(String dataType);
}
