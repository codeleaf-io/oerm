package io.codeleaf.oerm.tasks.data;

import io.codeleaf.oerm.Count;
import io.codeleaf.oerm.tasks.DataTask;
import io.codeleaf.oerm.tasks.FieldsBasedTask;
import io.codeleaf.oerm.tasks.SelectTask;
import io.codeleaf.oerm.tasks.UpdateTask;

public interface UpdateFieldsTask<E, K, D, F, V, S> extends
        DataTask<E, K, D, F, V, S, Count>,
        UpdateTask<E, K, D, F, V, S, Count>,
        SelectTask<E, K, D, F, V, S, Count>,
        FieldsBasedTask<E, K, D, F, V, S, Count> {

    interface Builder<
            B extends Builder<B, T, E, K, D, F, V, S>,
            T extends UpdateFieldsTask<E, K, D, F, V, S>,
            E, K, D, F, V, S> extends
            DataTask.Builder<B, T, E, K, D, F, V, S, Count>,
            UpdateTask.Builder<B, T, E, K, D, F, V, S, Count>,
            SelectTask.Builder<B, T, E, K, D, F, V, S, Count>,
            FieldsBasedTask.Builder<B, T, E, K, D, F, V, S, Count> {
    }
}
