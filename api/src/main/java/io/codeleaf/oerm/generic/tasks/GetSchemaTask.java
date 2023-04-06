package io.codeleaf.oerm.generic.tasks;

public interface GetSchemaTask<D, S> extends DataTypeTask<D, S> {

    D getDataType();

    interface Builder<
            B extends Builder<B, T, D, S>,
            T extends GetSchemaTask<D, S>,
            D,
            S> extends DataTypeTask.Builder<B, T, D, S> {

        B withDataType(D dataType);
    }

}
