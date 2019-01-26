package io.codeleaf.dal.tasks;

import io.codeleaf.modeling.task.Task;

public interface DataTask<D, O> extends Task<O> {

    D getDataType();

    interface Builder<
            B extends Builder<B, T, D, O>,
            T extends DataTask<D, O>,
            D,
            O> {

        B withDataType(D dataType);

        T build();
    }
}
