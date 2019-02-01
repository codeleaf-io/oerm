package io.codeleaf.oerm.object.mapping;

import io.codeleaf.oerm.object.Entity;

import java.lang.reflect.Method;
import java.util.Map;

public final class DefaultFieldValueGenerator implements FieldValueGenerator {

    @Override
    public boolean supportsType(Class<?> fieldValueType) {
        return false;
    }

    @Override
    public <T> T generate(Class<? extends Entity> entityType, Method method, Map<Method, Object> fields) {
        return null;
    }
}
