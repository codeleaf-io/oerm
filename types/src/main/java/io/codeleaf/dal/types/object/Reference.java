package io.codeleaf.dal.types.object;

import java.util.Objects;

public final class Reference<E extends Entity> {

    public static <E extends Entity> Reference<E> create(String identifier, Class<E> entityType) {
        Objects.requireNonNull(identifier);
        if (identifier.isEmpty()) {
            throw new IllegalArgumentException("No empty identifiers allowed!");
        }
        Objects.requireNonNull(entityType);
        return new Reference<>(identifier, entityType);
    }

    private final String identifier;
    private final Class<E> entityType;

    private Reference(String identifier, Class<E> entityType) {
        this.identifier = identifier;
        this.entityType = entityType;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Class<E> getEntityType() {
        return entityType;
    }
}
