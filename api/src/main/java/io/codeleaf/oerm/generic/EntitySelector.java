package io.codeleaf.oerm.generic;

import io.codeleaf.modeling.selection.Selection;

public interface EntitySelector<E, K, D> {

    default Selection select(E entity) {
        return select(getDataType(entity), getEntityId(entity));
    }

    Selection select(D dataType, K entityId);

    K getEntityId(E entity);

    D getDataType(E entity);
}
