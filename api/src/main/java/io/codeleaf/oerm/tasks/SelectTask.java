package io.codeleaf.oerm.tasks;

import io.codeleaf.modeling.selection.SelectableBuilder;
import io.codeleaf.modeling.selection.Selection;

public interface SelectTask<D, F, V, O> extends DataTask<D, O> {

    Selection getSelection();

    Class<F> getFieldNameType();

    Class<V> getFieldValueType();

    interface Builder<
            B extends Builder<B, T, D, F, V, O>,
            T extends SelectTask<D, F, V, O>,
            D,
            F,
            V,
            O>
            extends DataTask.Builder<B, T, D, O>, SelectableBuilder<F, V, B> {
    }
}
