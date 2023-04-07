package io.codeleaf.oerm.tasks;

import io.codeleaf.oerm.Count;

public interface RemoveTask<D, F, V>
        extends
        SelectTask<D, F, V, Count>,
        WriteTask<D, Count> {

    interface Builder<
            B extends Builder<B, T, D, F, V>,
            T extends RemoveTask<D, F, V>,
            D,
            F,
            V>
            extends
            SelectTask.Builder<B, T, D, F, V, Count>,
            WriteTask.Builder<B, T, D, Count> {
    }
}
