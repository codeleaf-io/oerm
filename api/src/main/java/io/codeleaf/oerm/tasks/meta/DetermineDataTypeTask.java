package io.codeleaf.oerm.tasks.meta;

import io.codeleaf.oerm.tasks.EntityBasedTask;
import io.codeleaf.oerm.tasks.MetaTask;

public interface DetermineDataTypeTask<E, K, D, F, V, S> extends
        MetaTask<E, K, D, F, V, S, D>,
        EntityBasedTask<E, K, D, F, V, S, D> {

    interface Builder<
            B extends Builder<B, T, E, K, D, F, V, S>,
            T extends DetermineDataTypeTask<E, K, D, F, V, S>,
            E, K, D, F, V, S> extends
            MetaTask.Builder<B, T, E, K, D, F, V, S, D>,
            EntityBasedTask.Builder<B, T, E, K, D, F, V, S, D> {
    }
}
