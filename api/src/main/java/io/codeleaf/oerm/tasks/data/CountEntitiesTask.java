package io.codeleaf.oerm.tasks.data;

import io.codeleaf.oerm.Count;
import io.codeleaf.oerm.tasks.DataTask;
import io.codeleaf.oerm.tasks.ReadTask;
import io.codeleaf.oerm.tasks.SelectTask;

public interface CountEntitiesTask<E, K, D, F, V, S> extends
        DataTask<E, K, D, F, V, S, Count>,
        ReadTask<E, K, D, F, V, S, Count>,
        SelectTask<E, K, D, F, V, S, Count> {

    interface Builder<
            B extends Builder<B, T, E, K, D, F, V, S>,
            T extends CountEntitiesTask<E, K, D, F, V, S>,
            E, K, D, F, V, S> extends
            DataTask.Builder<B, T, E, K, D, F, V, S, Count>,
            SelectTask.Builder<B, T, E, K, D, F, V, S, Count> {
    }
}
