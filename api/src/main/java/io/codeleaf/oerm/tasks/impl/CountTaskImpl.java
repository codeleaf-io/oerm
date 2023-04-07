package io.codeleaf.oerm.tasks.impl;

import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.Count;
import io.codeleaf.oerm.tasks.CountTask;

public final class CountTaskImpl<D, F, V> extends AbstractSelectTask<D, F, V, Count> implements CountTask<D, F, V, Count> {

    private CountTaskImpl(Selection selection, Class<F> fieldNameType, Class<V> fieldValueType, D dataType) {
        super(selection, fieldNameType, fieldValueType, dataType, Count.class);
    }

    public static final class Builder<D, F, V>
            extends AbstractSelectTask.Builder<Builder<D, F, V>, CountTaskImpl<D, F, V>, D, F, V, Count>
            implements CountTask.Builder<Builder<D, F, V>, CountTaskImpl<D, F, V>, D, F, V, Count> {

        public Builder(D dataType, Class<F> fieldNameType, Class<V> fieldValueType) {
            super(dataType, fieldNameType, fieldValueType);
        }

        @Override
        public CountTaskImpl<D, F, V> build() {
            validate();
            return new CountTaskImpl<>(selection, fieldNameType, fieldValueType, dataType);
        }
    }
}
