package io.codeleaf.oerm.tasks.meta;

import io.codeleaf.oerm.tasks.GetTask;
import io.codeleaf.oerm.tasks.MetaTask;
import io.codeleaf.oerm.tasks.ReadTask;

import java.util.Set;

public interface GetDataTypesTask<E, K, D, F, V, S> extends
        MetaTask<E, K, D, F, V, S, Set<D>>,
        GetTask<E, K, D, F, V, S, Set<D>> {

    interface Builder<
            B extends Builder<B, T, E, K, D, F, V, S>,
            T extends GetDataTypesTask<E, K, D, F, V, S>,
            E, K, D, F, V, S> extends
            MetaTask.Builder<B, T, E, K, D, F, V, S, Set<D>> {
    }
}
