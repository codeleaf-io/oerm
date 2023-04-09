package io.codeleaf.oerm.tasks.meta;

import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.tasks.MetaTask;
import io.codeleaf.oerm.tasks.TypeBasedTask;

public interface SelectEntityTask<E, K, D, F, V, S> extends
        MetaTask<E, K, D, F, V, S, Selection>,
        TypeBasedTask<E, K, D, F, V, S, Selection> {

    K getEntityId();

    interface Builder<
            B extends SelectEntityTask.Builder<B, T, E, K, D, F, V, S>,
            T extends SelectEntityTask<E, K, D, F, V, S>,
            E, K, D, F, V, S> extends
            MetaTask.Builder<B, T, E, K, D, F, V, S, Selection>,
            TypeBasedTask.Builder<B, T, E, K, D, F, V, S, Selection> {

        B withEntityId(K entityId);
    }
}
