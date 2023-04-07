package io.codeleaf.oerm.tasks;

public interface RemoveDataTypeTask<D> extends DataTypeTask<D, Boolean> {

    D getDataType();

    interface Builder<
            B extends Builder<B, T, D>,
            T extends RemoveDataTypeTask<D>,
            D> extends DataTypeTask.Builder<B, T, D, Boolean> {

        B withDataType(D dataType);
    }

}
