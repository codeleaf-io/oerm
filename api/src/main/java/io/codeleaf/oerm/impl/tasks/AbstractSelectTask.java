package io.codeleaf.oerm.impl.tasks;

import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.tasks.SelectTask;

import java.util.Objects;

public abstract class AbstractSelectTask<E, K, D, F, V, S, O>
        extends AbstractTypeBasedTask<E, K, D, F, V, S, O>
        implements SelectTask<E, K, D, F, V, S, O> {

    private final Selection selection;

    protected AbstractSelectTask(DataModelTypes<E, K, D, F, V, S> dataModelTypes, Class<O> outputType, D dataType, Selection selection) {
        super(dataModelTypes, outputType, dataType);
        this.selection = selection;
    }

    @Override
    public Selection getSelection() {
        return selection;
    }

    public static abstract class Builder<
            B extends Builder<B, T, E, K, D, F, V, S, O>,
            T extends AbstractSelectTask<E, K, D, F, V, S, O>,
            E, K, D, F, V, S, O> extends
            AbstractTypeBasedTask.Builder<B, T, E, K, D, F, V, S, O>
            implements SelectTask.Builder<B, T, E, K, D, F, V, S, O> {

        protected Selection selection;

        public Builder(DataModelTypes<E, K, D, F, V, S> dataModelTypes) {
            super(dataModelTypes);
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
            if (selection == null) {
                throw new IllegalStateException();
            }
        }
    }
}
