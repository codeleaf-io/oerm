package io.codeleaf.oerm.tasks;

import io.codeleaf.oerm.SearchCursor;

public interface IterateTask<E, K, D, F, V, S, O extends SearchCursor<E>> extends ListTask<E, K, D, F, V, S, O> {

    int getBufferSize();

    interface Builder<
            B extends Builder<B, T, E, K, D, F, V, S, O>,
            T extends IterateTask<E, K, D, F, V, S, O>,
            E, K, D, F, V, S, O extends SearchCursor<E>>
            extends ListTask.Builder<B, T, E, K, D, F, V, S, O> {

        B withBufferSize(int bufferSize);
    }
}
