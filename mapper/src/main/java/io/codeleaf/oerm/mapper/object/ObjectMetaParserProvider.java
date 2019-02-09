package io.codeleaf.oerm.mapper.object;

import io.codeleaf.oerm.object.Entity;

import java.util.HashMap;
import java.util.Map;

public final class ObjectMetaParserProvider {

    private final Map<Class<? extends Entity>, ObjectMetaParser<?>> parsers = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <E extends Entity> ObjectMetaParser<E> getObjectMetaParser(Class<E> entityType) {
        return (ObjectMetaParser<E>) parsers.computeIfAbsent(entityType, ObjectMetaParser::new);
    }
}
