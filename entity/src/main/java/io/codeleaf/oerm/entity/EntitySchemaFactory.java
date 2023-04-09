package io.codeleaf.oerm.entity;

public interface EntitySchemaFactory<T> {

    EntitySchema createEntitySchema(T definition);
}
