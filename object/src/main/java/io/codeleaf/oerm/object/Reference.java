package io.codeleaf.oerm.object;

import java.util.Objects;

public final class Reference<E extends Entity> {

    private final String identifier;
    private final Class<? extends E> entityType;
    private Reference(String identifier, Class<? extends E> entityType) {
        this.identifier = identifier;
        this.entityType = entityType;
    }

    public static <E extends Entity> Reference<E> create(String identifier, Class<? extends E> entityType) {
        Objects.requireNonNull(identifier);
        if (identifier.isEmpty()) {
            throw new IllegalArgumentException("No empty identifiers allowed!");
        }
        Objects.requireNonNull(entityType);
        return new Reference<>(identifier, entityType);
    }

    public String getIdentifier() {
        return identifier;
    }

    public Class<? extends E> getEntityType() {
        return entityType;
    }
}
