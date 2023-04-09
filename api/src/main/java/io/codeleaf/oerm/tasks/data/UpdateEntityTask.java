package io.codeleaf.oerm.tasks.data;

import io.codeleaf.oerm.tasks.DataTask;
import io.codeleaf.oerm.tasks.EntityBasedTask;
import io.codeleaf.oerm.tasks.UpdateTask;

public interface UpdateEntityTask<E, K, D, F, V, S> extends
        DataTask<E, K, D, F, V, S, Void>,
        UpdateTask<E, K, D, F, V, S, Void>,
        EntityBasedTask<E, K, D, F, V, S, Void> {

    interface Builder<
            B extends Builder<B, T, E, K, D, F, V, S>,
            T extends UpdateEntityTask<E, K, D, F, V, S>,
            E, K, D, F, V, S> extends
            DataTask.Builder<B, T, E, K, D, F, V, S, Void>,
            EntityBasedTask.Builder<B, T, E, K, D, F, V, S, Void> {
    }
}
