package io.codeleaf.oerm.tasks.data;

import io.codeleaf.oerm.tasks.AddTask;
import io.codeleaf.oerm.tasks.DataTask;
import io.codeleaf.oerm.tasks.FieldsBasedTask;

public interface CreateEntityTask<E, K, D, F, V, S> extends
        DataTask<E, K, D, F, V, S, K>,
        AddTask<E, K, D, F, V, S, K>,
        FieldsBasedTask<E, K, D, F, V, S, K> {

    interface Builder<
            B extends Builder<B, T, E, K, D, F, V, S>,
            T extends CreateEntityTask<E, K, D, F, V, S>,
            E, K, D, F, V, S> extends
            DataTask.Builder<B, T, E, K, D, F, V, S, K>,
            FieldsBasedTask.Builder<B, T, E, K, D, F, V, S, K> {
    }
}
