package io.codeleaf.oerm.generic;

import io.codeleaf.modeling.selection.Selection;

public interface EntitySelector<E, K, D> {

    Selection select(D dataType, K entityId);

    D getDataType(E entity);
}
