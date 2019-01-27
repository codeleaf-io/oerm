package io.codeleaf.dal.generic;

import io.codeleaf.dal.SearchCursor;
import io.codeleaf.dal.SearchCursorAndCount;
import io.codeleaf.dal.SearchPage;
import io.codeleaf.dal.SearchPageAndCount;
import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.modeling.selection.SelectionBuilder;

import java.util.Map;

public interface TypedRepository<E, K, D, F, V> {

    RepositoryTypes<E, K, D, F, V> getGenericTypes();

    D getDataType();

    SelectionBuilder<F, V, Selection> getSelectionFactory();

    K create(E object);

    K create(Map<F, V> fields);

    boolean exists(K objectId);

    E retrieve(K objectId);

    E retrieveUnique(Selection selection);

    boolean isUnique(Selection selection);

    long count(Selection selection);

    SearchCursor<E> search(Selection selection);

    SearchPage<E> search(Selection selection, long offset, int limit);

    SearchCursorAndCount<E> searchAndCount(Selection selection);

    SearchPageAndCount<E> searchAndCount(Selection selection, long offset, int limit);

    void update(K objectId, E object);

    void update(Selection selection, Map<F, V> fields);

    void delete(K objectId);
}
