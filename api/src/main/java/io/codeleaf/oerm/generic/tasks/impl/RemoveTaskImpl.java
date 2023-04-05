package io.codeleaf.oerm.generic.tasks.impl;

import io.codeleaf.oerm.generic.tasks.RemoveTask;
import io.codeleaf.modeling.selection.Selection;

public final class RemoveTaskImpl<D, F, V> extends AbstractSelectTask<D, F, V, Void> implements RemoveTask<D, F, V, Void> {

    private RemoveTaskImpl(Selection selection, Class<F> fieldNameType, Class<V> fieldValueType, D dataType) {
        super(selection, fieldNameType, fieldValueType, dataType, Void.class);
    }

    public static final class Builder<D, F, V> extends AbstractSelectTask.Builder<Builder<D, F, V>, RemoveTaskImpl<D, F, V>, D, F, V, Void> {

        public Builder() {
        }

        public Builder(D dataType, Class<F> fieldNameType, Class<V> fieldValueType) {
            super(dataType, fieldNameType, fieldValueType);
        }

        @Override
        public RemoveTaskImpl<D, F, V> build() {
            validate();
            return new RemoveTaskImpl<>(selection, fieldNameType, fieldValueType, dataType);
        }
    }
}
