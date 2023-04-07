package io.codeleaf.oerm.tasks;

public interface AddDataTypeTask<D, S> extends DataTypeTask<D, Void> {

    Class<S> getEntitySchemaType();

    D getDataType();

    S getSchema();

    interface Builder<
            B extends Builder<B, T, D, S>,
            T extends AddDataTypeTask<D, S>,
            D,
            S> extends DataTypeTask.Builder<B, T, D, Void> {

        B withDataType(D dataType);

        B withSchema(S schema);

    }

}
