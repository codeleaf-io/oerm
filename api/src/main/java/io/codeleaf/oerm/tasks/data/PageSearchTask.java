package io.codeleaf.oerm.tasks.data;

import io.codeleaf.oerm.SearchPage;
import io.codeleaf.oerm.tasks.*;

public interface PageSearchTask<E, K, D, F, V, S, O extends SearchPage<E>> extends
        DataTask<E, K, D, F, V, S, O>,
        PaginateTask<E, K, D, F, V, S, O>,
        SelectTask<E, K, D, F, V, S, O>,
        ProjectTask<E, K, D, F, V, S, O> {

    interface Builder<
            B extends Builder<B, T, E, K, D, F, V, S, O>,
            T extends PageSearchTask<E, K, D, F, V, S, O>,
            E, K, D, F, V, S, O extends SearchPage<E>> extends
            DataTask.Builder<B, T, E, K, D, F, V, S, O>,
            PaginateTask.Builder<B, T, E, K, D, F, V, S, O>,
            SelectTask.Builder<B, T, E, K, D, F, V, S, O>,
            ProjectTask.Builder<B, T, E, K, D, F, V, S, O> {
    }
}
