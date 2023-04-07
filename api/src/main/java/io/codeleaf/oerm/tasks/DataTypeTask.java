package io.codeleaf.oerm.tasks;

public interface DataTypeTask<D, O> extends DatabaseTask<O> {

    Class<D> getDataTypeType();

    interface Builder<
            B extends Builder<B, T, D, O>,
            T extends DataTypeTask<D, O>,
            D,
            O> {

        T build();
    }
}
