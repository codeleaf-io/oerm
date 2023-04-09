package io.codeleaf.oerm.object.mapping;

import io.codeleaf.modeling.data.ValueType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.object.Entity;

import java.lang.reflect.Method;
import java.util.Map;

// TODO: implement
public final class DefaultObjectFieldMapper implements ObjectFieldMapper {

    @Override
    public ValueWithType<?> mapObjectField(Class<? extends Entity> entityType, Method method, String fieldName, Map<Method, Object> objectFields) {
        return null;
    }

    @Override
    public Object mapEntityField(Class<? extends Entity> entityType, Method method, String fieldName, Map<String, ValueWithType<?>> entityFields) {
        return null;
    }

    @Override
    public ValueType getType(Class<? extends Entity> entityType, Method method, String fieldName) {
        return null;
    }
}
