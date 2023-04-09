package io.codeleaf.oerm.tasks.data;

import io.codeleaf.oerm.Count;
import io.codeleaf.oerm.tasks.DataTask;
import io.codeleaf.oerm.tasks.DatabaseTask;
import io.codeleaf.oerm.tasks.SelectTask;
import io.codeleaf.oerm.tasks.WriteTask;

public interface RemoveEntitiesTask<E, K, D, F, V, S> extends
        DataTask<E, K, D, F, V, S, Count>,
        WriteTask<E, K, D, F, V, S, Count>,
        SelectTask<E, K, D, F, V, S, Count> {

    interface Builder<
            B extends Builder<B, T, E, K, D, F, V, S>,
            T extends RemoveEntitiesTask<E, K, D, F, V, S>,
            E, K, D, F, V, S> extends
            DataTask.Builder<B, T, E, K, D, F, V, S, Count>,
            DatabaseTask.Builder<B, T, E, K, D, F, V, S, Count>,
            SelectTask.Builder<B, T, E, K, D, F, V, S, Count> {
    }
}
