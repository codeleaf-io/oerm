package io.codeleaf.oerm.tasks.meta;

import io.codeleaf.oerm.tasks.DeleteTask;
import io.codeleaf.oerm.tasks.MetaTask;
import io.codeleaf.oerm.tasks.TypeBasedTask;

public interface RemoveDataTypeTask<E, K, D, F, V, S> extends
        MetaTask<E, K, D, F, V, S, Boolean>,
        DeleteTask<E, K, D, F, V, S, Boolean>,
        TypeBasedTask<E, K, D, F, V, S, Boolean> {

    D getDataType();

    interface Builder<
            B extends Builder<B, T, E, K, D, F, V, S>,
            T extends RemoveDataTypeTask<E, K, D, F, V, S>,
            E, K, D, F, V, S> extends
            MetaTask.Builder<B, T, E, K, D, F, V, S, Boolean>,
            TypeBasedTask.Builder<B, T, E, K, D, F, V, S, Boolean> {
    }
}
