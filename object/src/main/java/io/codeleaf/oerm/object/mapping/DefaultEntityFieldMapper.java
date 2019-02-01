package io.codeleaf.oerm.object.mapping;

import io.codeleaf.modeling.data.ValueType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.object.Entity;

import java.lang.reflect.Method;
import java.util.Map;

public final class DefaultEntityFieldMapper implements EntityFieldMapper {

    @Override
    public ValueWithType<?> map(Class<? extends Entity> entityType, Method method, String fieldName, Map<Method, Object> fields) {
        return null;
    }

    @Override
    public ValueType getType(Class<? extends Entity> entityType, Method method, String fieldName) {
        return null;
    }

}
