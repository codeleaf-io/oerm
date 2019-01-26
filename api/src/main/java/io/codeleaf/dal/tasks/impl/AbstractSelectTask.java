package io.codeleaf.dal.tasks.impl;

import io.codeleaf.dal.tasks.SelectTask;
import io.codeleaf.modeling.selection.Selection;

import java.util.Objects;

public abstract class AbstractSelectTask<D, F, V, O> implements SelectTask<D, F, V, O> {

    private final Selection selection;
    private final Class<F> fieldNameType;
    private final Class<V> fieldValueType;
    private final D dataType;
    private final Class<O> outputType;

    protected AbstractSelectTask(Selection selection, Class<F> fieldNameType, Class<V> fieldValueType, D dataType, Class<O> outputType) {
        this.selection = selection;
        this.fieldNameType = fieldNameType;
        this.fieldValueType = fieldValueType;
        this.dataType = dataType;
        this.outputType = outputType;
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

    @Override
    public D getDataType() {
        return dataType;
    }

    @Override
    public Class<O> getOutputType() {
        return outputType;
    }

    @SuppressWarnings("unchecked")
    public static abstract class Builder<
            B extends Builder<B, T, D, F, V, O>,
            T extends AbstractSelectTask<D, F, V, O>,
            D,
            F,
            V,
            O
            > implements SelectTask.Builder<B, T, D, F, V, O> {

        protected D dataType;
        protected Class<F> fieldNameType;
        protected Class<V> fieldValueType;
        protected Selection selection;

        public Builder() {
        }

        public Builder(D dataType, Class<F> fieldNameType, Class<V> fieldValueType) {
            this.dataType = dataType;
            this.fieldNameType = fieldNameType;
            this.fieldValueType = fieldValueType;
        }

        @Override
        public B withFieldNameType(Class<F> fieldNameType) {
            Objects.requireNonNull(fieldNameType);
            this.fieldNameType = fieldNameType;
            return (B) this;
        }

        @Override
        public B withFieldValueType(Class<V> fieldValueType) {
            Objects.requireNonNull(fieldValueType);
            this.fieldValueType = fieldValueType;
            return (B) this;
        }

        @Override
        public B withDataType(D dataType) {
            Objects.requireNonNull(dataType);
            this.dataType = dataType;
            return (B) this;
        }

        @Override
        public void select(Selection selection) {
            Objects.requireNonNull(selection);
            if (this.selection != null) {
                throw new IllegalStateException("Already selection set!");
            }
            this.selection = selection;
        }

        protected void validate() {
            if (fieldNameType == null || fieldValueType == null || dataType == null || selection == null) {
                throw new IllegalStateException();
            }
        }
    }
}
