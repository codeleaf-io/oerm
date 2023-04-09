package io.codeleaf.oerm.tasks;

import io.codeleaf.oerm.Count;

public interface CountTask<E, K, D, F, V, S, O extends Count> extends ReadTask<E, K, D, F, V, S, O> {

    interface Builder<
            B extends Builder<B, T, E, K, D, F, V, S, O>,
            T extends CountTask<E, K, D, F, V, S, O>,
            E, K, D, F, V, S, O extends Count>
            extends ReadTask.Builder<B, T, E, K, D, F, V, S, O> {
    }
}
