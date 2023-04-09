package io.codeleaf.oerm;

import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.modeling.selection.SelectionBuilder;

import java.util.Map;

public interface TypedRepository<E, K, D, F, V, S> {

    DataModelTypes<E, K, D, F, V, S> getDataModelTypes();

    D getDataType();

    S getDataSchema();

    Selection select(K entityId);

    SelectionBuilder<F, V, Selection> getSelectionFactory();

    K create(E entity);

    K create(Map<F, V> fields);

    boolean exists(K entityId);

    E get(K entityId);

    E retrieve(Selection selection);

    boolean isUnique(Selection selection);

    long count(Selection selection);

    SearchCursor<E> search(Selection selection);

    SearchPage<E> search(Selection selection, long offset, int limit);

    SearchCursorAndCount<E> searchAndCount(Selection selection);

    SearchPageAndCount<E> searchAndCount(Selection selection, long offset, int limit);

    void update(E entity);

    long update(Selection selection, Map<F, V> fields);

    void delete(K entityId);
}
