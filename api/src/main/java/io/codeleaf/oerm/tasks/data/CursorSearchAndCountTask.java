package io.codeleaf.oerm.tasks.data;

import io.codeleaf.oerm.SearchCursorAndCount;
import io.codeleaf.oerm.tasks.*;

public interface CursorSearchAndCountTask<E, K, D, F, V, S, O extends SearchCursorAndCount<E>> extends
        DataTask<E, K, D, F, V, S, O>,
        IterateTask<E, K, D, F, V, S, O>,
        SelectTask<E, K, D, F, V, S, O>,
        ProjectTask<E, K, D, F, V, S, O>,
        CountTask<E, K, D, F, V, S, O> {

    interface Builder<
            B extends Builder<B, T, E, K, D, F, V, S, O>,
            T extends CursorSearchAndCountTask<E, K, D, F, V, S, O>,
            E, K, D, F, V, S, O extends SearchCursorAndCount<E>> extends
            DataTask.Builder<B, T, E, K, D, F, V, S, O>,
            IterateTask.Builder<B, T, E, K, D, F, V, S, O>,
            SelectTask.Builder<B, T, E, K, D, F, V, S, O>,
            ProjectTask.Builder<B, T, E, K, D, F, V, S, O>,
            CountTask.Builder<B, T, E, K, D, F, V, S, O> {
    }
}
