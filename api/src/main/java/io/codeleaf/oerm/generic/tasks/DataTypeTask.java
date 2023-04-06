package io.codeleaf.oerm.generic.tasks;

import io.codeleaf.modeling.task.Task;

public interface DataTypeTask<D, O> extends Task<O> {

    interface Builder<
            B extends Builder<B, T, D, O>,
            T extends DataTypeTask<D, O>,
            D,
            O> {

        T build();
    }
}
