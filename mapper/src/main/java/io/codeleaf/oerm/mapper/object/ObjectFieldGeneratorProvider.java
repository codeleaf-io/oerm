package io.codeleaf.oerm.mapper.object;

import io.codeleaf.oerm.object.mapping.ObjectFieldGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class ObjectFieldGeneratorProvider {

    private final Map<Class<? extends ObjectFieldGenerator>, ObjectFieldGenerator> generators = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T extends ObjectFieldGenerator> T get(Class<T> generatorType) {
        Objects.requireNonNull(generatorType);
        return (T) generators.computeIfAbsent(generatorType, (generatorClass) -> {
            try {
                return generatorClass.newInstance();
            } catch (InstantiationException | IllegalAccessException cause) {
                throw new IllegalStateException("Can't instantiate mapper: " + generatorClass.getCanonicalName());
            }
        });
    }
}
