package io.codeleaf.oerm.object;

public final class UnknownType implements Entity {
    @Override
    public String getIdentifier() {
        throw new IllegalStateException();
    }

    @Override
    public Meta getMeta() {
        throw new IllegalStateException();
    }
}
