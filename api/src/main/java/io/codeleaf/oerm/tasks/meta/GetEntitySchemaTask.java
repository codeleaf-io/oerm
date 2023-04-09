package io.codeleaf.oerm.tasks.meta;

import io.codeleaf.oerm.tasks.GetTask;
import io.codeleaf.oerm.tasks.MetaTask;
import io.codeleaf.oerm.tasks.TypeBasedTask;

public interface GetEntitySchemaTask<E, K, D, F, V, S> extends
        MetaTask<E, K, D, F, V, S, S>,
        GetTask<E, K, D, F, V, S, S>,
        TypeBasedTask<E, K, D, F, V, S, S> {

    D getDataType();

    interface Builder<
            B extends Builder<B, T, E, K, D, F, V, S>,
            T extends GetEntitySchemaTask<E, K, D, F, V, S>,
            E, K, D, F, V, S>
            extends TypeBasedTask.Builder<B, T, E, K, D, F, V, S, S> {
    }

}
