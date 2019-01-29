package io.codeleaf.oerm.generic.tasks.impl;

import io.codeleaf.oerm.generic.tasks.DeleteTask;
import io.codeleaf.modeling.selection.Selection;

public final class DeleteTaskImpl<D, F, V> extends AbstractSelectTask<D, F, V, Void> implements DeleteTask<D, F, V, Void> {

    private DeleteTaskImpl(Selection selection, Class<F> fieldNameType, Class<V> fieldValueType, D dataType) {
        super(selection, fieldNameType, fieldValueType, dataType, Void.class);
    }

    public static final class Builder<D, F, V> extends AbstractSelectTask.Builder<Builder<D, F, V>, DeleteTaskImpl<D, F, V>, D, F, V, Void> {

        public Builder() {
        }

        public Builder(D dataType, Class<F> fieldNameType, Class<V> fieldValueType) {
            super(dataType, fieldNameType, fieldValueType);
        }

        @Override
        public DeleteTaskImpl<D, F, V> build() {
            validate();
            return new DeleteTaskImpl<>(selection, fieldNameType, fieldValueType, dataType);
        }
    }
}
