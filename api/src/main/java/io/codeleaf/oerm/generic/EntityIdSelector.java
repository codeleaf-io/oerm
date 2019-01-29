package io.codeleaf.oerm.generic;

import io.codeleaf.modeling.selection.Selection;

@FunctionalInterface
public interface EntityIdSelector<D, K> {

    Selection select(D dataType, K entityId);

}
