package io.codeleaf.oerm.tasks;

import io.codeleaf.modeling.selection.SelectableBuilder;
import io.codeleaf.modeling.selection.Selection;

public interface SelectTask<E, K, D, F, V, S, O> extends DatabaseTask<E, K, D, F, V, S, O> {

    Selection getSelection();

    interface Builder<
            B extends Builder<B, T, E, K, D, F, V, S, O>,
            T extends SelectTask<E, K, D, F, V, S, O>,
            E, K, D, F, V, S, O>
            extends DatabaseTask.Builder<B, T, E, K, D, F, V, S, O>, SelectableBuilder<F, V, B> {
    }
}
