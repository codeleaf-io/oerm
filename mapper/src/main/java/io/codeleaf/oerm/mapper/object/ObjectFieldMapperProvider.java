package io.codeleaf.oerm.mapper.object;

import io.codeleaf.oerm.object.mapping.DefaultObjectFieldMapper;
import io.codeleaf.oerm.object.mapping.Mapping;
import io.codeleaf.oerm.object.mapping.ObjectFieldMapper;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public final class ObjectFieldMapperProvider {

    private final ObjectFieldMapper DEFAULT_MAPPER = new DefaultObjectFieldMapper();

    private final Map<Class<? extends ObjectFieldMapper>, ObjectFieldMapper> mappers = new HashMap<>();

    public ObjectFieldMapper get(Mapping mapping) {
        if (mapping == null) {
            return DEFAULT_MAPPER;
        }
        return mappers.computeIfAbsent(mapping.mapper(), (mapper) -> {
            try {
                return mapper.getConstructor().newInstance();
            } catch (InstantiationException | NoSuchMethodException | IllegalAccessException |
                     InvocationTargetException cause) {
                throw new IllegalStateException("Can't instantiate mapper: " + mapper.getCanonicalName());
            }
        });
    }
}
