package io.codeleaf.oerm.tasks.data;

import io.codeleaf.oerm.SearchCursor;
import io.codeleaf.oerm.tasks.DataTask;
import io.codeleaf.oerm.tasks.IterateTask;
import io.codeleaf.oerm.tasks.ProjectTask;
import io.codeleaf.oerm.tasks.SelectTask;

public interface CursorSearchTask<E, K, D, F, V, S, O extends SearchCursor<E>> extends
        DataTask<E, K, D, F, V, S, O>,
        IterateTask<E, K, D, F, V, S, O>,
        SelectTask<E, K, D, F, V, S, O>,
        ProjectTask<E, K, D, F, V, S, O> {

    interface Builder<
            B extends Builder<B, T, E, K, D, F, V, S, O>,
            T extends CursorSearchTask<E, K, D, F, V, S, O>,
            E, K, D, F, V, S, O extends SearchCursor<E>> extends
            DataTask.Builder<B, T, E, K, D, F, V, S, O>,
            IterateTask.Builder<B, T, E, K, D, F, V, S, O>,
            SelectTask.Builder<B, T, E, K, D, F, V, S, O>,
            ProjectTask.Builder<B, T, E, K, D, F, V, S, O> {
    }
}
