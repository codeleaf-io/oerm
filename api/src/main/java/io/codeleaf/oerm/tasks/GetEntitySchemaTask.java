package io.codeleaf.oerm.tasks;

public interface GetEntitySchemaTask<D, S> extends DataTypeTask<D, S> {

    D getDataType();

    Class<S> getEntitySchemaType();

    interface Builder<
            B extends Builder<B, T, D, S>,
            T extends GetEntitySchemaTask<D, S>,
            D,
            S> extends DataTypeTask.Builder<B, T, D, S> {

        B withDataType(D dataType);
    }

}
