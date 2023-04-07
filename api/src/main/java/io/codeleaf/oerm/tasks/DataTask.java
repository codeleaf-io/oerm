package io.codeleaf.oerm.tasks;

public interface DataTask<D, O> extends DatabaseTask<O> {

    D getDataType();

    interface Builder<
            B extends Builder<B, T, D, O>,
            T extends DataTask<D, O>,
            D,
            O> {

        T build();
    }
}
