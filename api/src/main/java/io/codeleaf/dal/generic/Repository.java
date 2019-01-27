package io.codeleaf.dal.generic;

import io.codeleaf.dal.*;
import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.modeling.selection.SelectionBuilder;

import java.util.Map;

public interface Repository<E, K, D, F, V> {

    RepositoryTypes<E, K, D, F, V> getGenericTypes();

    SelectionBuilder<F, V, Selection> getSelectionFactory();

    K create(D dataType, E object);

    K create(D dataType, Map<F, V> fields);

    boolean exists(D dataType, K objectId);

    E retrieve(D dataType, K objectId);

    E retrieveUnique(D dataType, Selection selection);

    boolean isUnique(D dataType, Selection selection);

    long count(D dataType, Selection selection);

    SearchCursor<E> search(D dataType, Selection selection);

    SearchPage<E> search(D dataType, Selection selection, long offset, int limit);

    SearchCursorAndCount<E> searchAndCount(D dataType, Selection selection);

    SearchPageAndCount<E> searchAndCount(D dataType, Selection selection, long offset, int limit);

    void update(D dataType, K objectId, E object);

    void update(D dataType, Selection selection, Map<F, V> fields);

    void delete(D dataType, K objectId);

}
