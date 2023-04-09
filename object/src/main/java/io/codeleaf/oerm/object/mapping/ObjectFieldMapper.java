package io.codeleaf.oerm.object.mapping;

import io.codeleaf.modeling.data.ValueType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.object.Entity;

import java.lang.reflect.Method;
import java.util.Map;

public interface ObjectFieldMapper {

    ValueWithType<?> mapObjectField(Class<? extends Entity> entityType, Method method, String fieldName, Map<Method, Object> objectFields);

    Object mapEntityField(Class<? extends Entity> entityType, Method method, String fieldName, Map<String, ValueWithType<?>> entityFields);

    ValueType getType(Class<? extends Entity> entityType, Method method, String fieldName);

}
