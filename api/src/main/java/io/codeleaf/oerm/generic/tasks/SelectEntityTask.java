package io.codeleaf.oerm.generic.tasks;

import io.codeleaf.modeling.selection.Selection;

public interface SelectEntityTask<K, D> extends DataTypeTask<D, Selection> {

    D getDataType();

    K getEntityId();

    Class<K> getEntityIdType();

    interface Builder<
            B extends SelectEntityTask.Builder<B, T, K, D>,
            T extends SelectEntityTask<K, D>,
            K,
            D> extends DataTypeTask.Builder<B, T, D, Selection> {

        B withDataType(D dataType);

        B withEntityId(K entityId);
    }

}
