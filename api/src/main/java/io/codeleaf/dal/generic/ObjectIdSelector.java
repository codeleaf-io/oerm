package io.codeleaf.dal.generic;

import io.codeleaf.modeling.selection.Selection;

@FunctionalInterface
public interface ObjectIdSelector<D, K> {

    Selection select(D dataType, K objectId);

}
