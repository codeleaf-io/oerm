package io.codeleaf.oerm.entity;

public interface EntitySchemaProvider {

    boolean hasSchema(String dataType);

    EntitySchema getSchema(String dataType);
}
