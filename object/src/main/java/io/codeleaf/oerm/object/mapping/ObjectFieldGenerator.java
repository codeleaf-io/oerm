package io.codeleaf.oerm.object.mapping;

import io.codeleaf.oerm.object.Entity;

import java.lang.reflect.Method;
import java.util.Map;

public interface ObjectFieldGenerator {

    boolean supportsType(Class<?> fieldType);

    <T> T generate(Class<? extends Entity> entityType, Method method, Map<Method, Object> fields);
}
