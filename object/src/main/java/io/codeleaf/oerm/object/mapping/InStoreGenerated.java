package io.codeleaf.oerm.object.mapping;

import io.codeleaf.oerm.object.Entity;

import java.lang.reflect.Method;
import java.util.Map;

public final class InStoreGenerated implements ObjectFieldGenerator {

    @Override
    public boolean supportsType(Class<?> fieldType) {
        return true;
    }

    @Override
    public <T> T generate(Class<? extends Entity> entityType, Method method, Map<Method, Object> fields) {
        throw new IllegalStateException("Generated in store, not in application!");
    }
}
