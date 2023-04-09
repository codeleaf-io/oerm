package io.codeleaf.oerm.tasks.meta;

import io.codeleaf.oerm.tasks.AddTask;
import io.codeleaf.oerm.tasks.MetaTask;
import io.codeleaf.oerm.tasks.TypeBasedTask;

public interface AddDataTypeTask<E, K, D, F, V, S> extends
        MetaTask<E, K, D, F, V, S, Boolean>,
        AddTask<E, K, D, F, V, S, Boolean>,
        TypeBasedTask<E, K, D, F, V, S, Boolean> {

    S getSchema();

    interface Builder<
            B extends Builder<B, T, E, K, D, F, V, S>,
            T extends AddDataTypeTask<E, K, D, F, V, S>,
            E, K, D, F, V, S>
            extends TypeBasedTask.Builder<B, T, E, K, D, F, V, S, Boolean> {

        B withSchema(S schema);
    }
}
