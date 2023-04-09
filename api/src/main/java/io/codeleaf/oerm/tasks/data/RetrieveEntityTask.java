package io.codeleaf.oerm.tasks.data;

import io.codeleaf.oerm.tasks.DataTask;
import io.codeleaf.oerm.tasks.GetTask;
import io.codeleaf.oerm.tasks.SelectTask;

public interface RetrieveEntityTask<E, K, D, F, V, S> extends
        DataTask<E, K, D, F, V, S, E>,
        GetTask<E, K, D, F, V, S, E>,
        SelectTask<E, K, D, F, V, S, E> {

    interface Builder<
            B extends Builder<B, T, E, K, D, F, V, S>,
            T extends RetrieveEntityTask<E, K, D, F, V, S>,
            E, K, D, F, V, S> extends
            DataTask.Builder<B, T, E, K, D, F, V, S, E>,
            SelectTask.Builder<B, T, E, K, D, F, V, S, E> {
    }
}
