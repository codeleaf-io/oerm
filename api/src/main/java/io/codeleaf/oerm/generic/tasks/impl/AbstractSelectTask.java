package io.codeleaf.oerm.generic.tasks.impl;

import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.generic.tasks.SelectTask;

import java.util.Objects;

public abstract class AbstractSelectTask<D, F, V, O> extends AbstractDataTask<D, O> implements SelectTask<D, F, V, O> {

    private final Selection selection;
    private final Class<F> fieldNameType;
    private final Class<V> fieldValueType;

    protected AbstractSelectTask(Selection selection, Class<F> fieldNameType, Class<V> fieldValueType, D dataType, Class<O> outputType) {
        super(dataType, outputType);
        this.selection = selection;
        this.fieldNameType = fieldNameType;
        this.fieldValueType = fieldValueType;
    }

    @Override
    public Selection getSelection() {
        return selection;
    }

    @Override
    public Class<F> getFieldNameType() {
        return fieldNameType;
    }

    @Override
    public Class<V> getFieldValueType() {
        return fieldValueType;
    }

    public static abstract class Builder<
            B extends Builder<B, T, D, F, V, O>,
            T extends AbstractSelectTask<D, F, V, O>,
            D,
            F,
            V,
            O
            > extends AbstractDataTask.Builder<B, T, D, O>
            implements SelectTask.Builder<B, T, D, F, V, O> {

        protected final Class<F> fieldNameType;
        protected final Class<V> fieldValueType;
        protected Selection selection;

        public Builder(D dataType, Class<F> fieldNameType, Class<V> fieldValueType) {
            super(dataType);
            this.fieldNameType = fieldNameType;
            this.fieldValueType = fieldValueType;
        }

        @Override
        public void select(Selection selection) {
            Objects.requireNonNull(selection);
            if (this.selection != null) {
                throw new IllegalStateException("Already selection set!");
            }
            this.selection = selection;
        }

        @Override
        protected void validate() {
            super.validate();
            if (fieldNameType == null || fieldValueType == null || selection == null) {
                throw new IllegalStateException();
            }
        }
    }
}
