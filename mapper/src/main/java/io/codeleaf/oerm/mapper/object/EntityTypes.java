package io.codeleaf.oerm.mapper.object;

import io.codeleaf.oerm.object.Entity;
import io.codeleaf.oerm.object.UnknownType;
import io.codeleaf.oerm.object.mapping.ObjectMeta;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public final class EntityTypes {

    private EntityTypes() {
    }

    public static String determineDataType(Class<? extends Entity> entityType) {
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

    public static Long getSerialVersionUID(Class<? extends Entity> entityType) {
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

    @SuppressWarnings("unchecked")
    public static Class<? extends Entity> determineEntityType(String dataType) {
        try {
            String[] parts = dataType.split(":");
            if (parts.length != 2) {
                throw new IllegalArgumentException();
            }
            String className = parts[0];
            Class<?> clazz = Class.forName(className);
            if (!Entity.class.isAssignableFrom(clazz)) {
                throw new IllegalArgumentException();
            }
            return (Class<? extends Entity>) clazz;
        } catch (ClassNotFoundException e) {
            return UnknownType.class;
        }
    }
}
