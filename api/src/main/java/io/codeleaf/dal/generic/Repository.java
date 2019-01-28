package io.codeleaf.dal.generic;

import io.codeleaf.dal.SearchCursor;
import io.codeleaf.dal.SearchCursorAndCount;
import io.codeleaf.dal.SearchPage;
import io.codeleaf.dal.SearchPageAndCount;
import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.modeling.selection.SelectionBuilder;

import java.util.Map;

public interface Repository<E, K, D, F, V> {

    RepositoryTypes<E, K, D, F, V> getGenericTypes();

    SelectionBuilder<F, V, Selection> getSelectionFactory();

    K create(D dataType, E entity);

    K create(D dataType, Map<F, V> fields);

    boolean exists(D dataType, K entityId);

    E retrieve(D dataType, K entityId);

    E retrieveUnique(D dataType, Selection selection);

    boolean isUnique(D dataType, Selection selection);

    long count(D dataType, Selection selection);

    SearchCursor<E> search(D dataType, Selection selection);

    SearchPage<E> search(D dataType, Selection selection, long offset, int limit);

    SearchCursorAndCount<E> searchAndCount(D dataType, Selection selection);

    SearchPageAndCount<E> searchAndCount(D dataType, Selection selection, long offset, int limit);

    void update(D dataType, K entityId, E entity);

    void update(D dataType, Selection selection, Map<F, V> fields);

    void delete(D dataType, K entityId);
}
