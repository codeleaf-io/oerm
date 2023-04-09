package io.codeleaf.oerm.tasks.data;

import io.codeleaf.oerm.tasks.AddTask;
import io.codeleaf.oerm.tasks.DataTask;
import io.codeleaf.oerm.tasks.EntityBasedTask;

public interface AddEntityTask<E, K, D, F, V, S> extends
        DataTask<E, K, D, F, V, S, K>,
        AddTask<E, K, D, F, V, S, K>,
        EntityBasedTask<E, K, D, F, V, S, K> {

    interface Builder<
            B extends Builder<B, T, E, K, D, F, V, S>,
            T extends AddEntityTask<E, K, D, F, V, S>,
            E, K, D, F, V, S> extends
            DataTask.Builder<B, T, E, K, D, F, V, S, K>,
            EntityBasedTask.Builder<B, T, E, K, D, F, V, S, K> {
    }
}
