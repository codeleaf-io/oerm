package io.codeleaf.dal.generic.tasks;

import io.codeleaf.dal.SearchCursor;

public interface IterateTask<D, F, H, O extends SearchCursor<H>> extends SearchTask<D, F, H, O> {

    int getBufferSize();

    interface Builder<
            B extends Builder<B, T, D, F, H, O>,
            T extends IterateTask<D, F, H, O>,
            D,
            F,
            H,
            O extends SearchCursor<H>>
            extends SearchTask.Builder<B, T, D, F, H, O> {

        B withBufferSize(int bufferSize);
    }
}
