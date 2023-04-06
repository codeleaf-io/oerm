package io.codeleaf.oerm.generic;

import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.modeling.selection.SelectionBuilder;
import io.codeleaf.oerm.SearchCursor;
import io.codeleaf.oerm.SearchCursorAndCount;
import io.codeleaf.oerm.SearchPage;
import io.codeleaf.oerm.SearchPageAndCount;

import java.util.Map;
import java.util.Set;

public interface Repository<E, K, D, F, V, S> {

    RepositoryTypes<E, K, D, F, V, S> getGenericTypes();

    SelectionBuilder<F, V, Selection> getSelectionFactory();

    Set<D> listSupportedDataTypes();

    boolean isSupportedDataType(D dataType);

    D getDataType(E entity);

    S getDataSchema(D dataType);

    Selection select(D dataType, K entityId);

    K add(E entity);

    K create(D dataType, Map<F, V> fields);

    boolean exists(D dataType, K entityId);

    E get(D dataType, K entityId);

    E retrieve(D dataType, Selection selection);

    boolean isUnique(D dataType, Selection selection);

    long count(D dataType, Selection selection);

    SearchCursor<E> search(D dataType, Selection selection);

    SearchPage<E> search(D dataType, Selection selection, long offset, int limit);

    SearchCursorAndCount<E> searchAndCount(D dataType, Selection selection);

    SearchPageAndCount<E> searchAndCount(D dataType, Selection selection, long offset, int limit);

    void update(E entity);

    long update(D dataType, Selection selection, Map<F, V> fields);

    void remove(D dataType, K entityId);
}
