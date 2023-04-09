package io.codeleaf.oerm.tasks;

import io.codeleaf.modeling.task.Task;
import io.codeleaf.oerm.DataModelTypes;

public interface DatabaseTask<E, K, D, F, V, S, O> extends Task<O> {

    DataModelTypes<E, K, D, F, V, S> getDataModelTypes();

    interface Builder<
            B extends DatabaseTask.Builder<B, T, E, K, D, F, V, S, O>,
            T extends DatabaseTask<E, K, D, F, V, S, O>,
            E, K, D, F, V, S, O> {

        T build();
    }

}
