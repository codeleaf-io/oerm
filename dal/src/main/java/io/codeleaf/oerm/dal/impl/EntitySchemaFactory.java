package io.codeleaf.oerm.dal.impl;

import io.codeleaf.oerm.entity.EntitySchema;

public class EntitySchemaFactory {

    EntitySchema createSchema(Class<?> typeClass, EntitySchemaRegistry registry) {
        if (typeClass.equals(Object.class)) {
            throw new IllegalArgumentException();
        }
        Class<?> superclass = typeClass.getSuperclass();
        if (!registry.contains(typeClass.getCanonicalName())) {
            registry.register(createSchema(typeClass.getSuperclass(), registry));
        }
        return null;
    }
}
