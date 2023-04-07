package io.codeleaf.oerm.tasks.impl;

import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.Count;
import io.codeleaf.oerm.tasks.RemoveTask;

public final class RemoveTaskImpl<D, F, V> extends AbstractSelectTask<D, F, V, Count> implements RemoveTask<D, F, V> {

    private RemoveTaskImpl(Selection selection, Class<F> fieldNameType, Class<V> fieldValueType, D dataType) {
        super(selection, fieldNameType, fieldValueType, dataType, Count.class);
    }

    public static final class Builder<D, F, V>
            extends AbstractSelectTask.Builder<Builder<D, F, V>, RemoveTaskImpl<D, F, V>, D, F, V, Count>
            implements RemoveTask.Builder<Builder<D, F, V>, RemoveTaskImpl<D, F, V>, D, F, V> {

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
