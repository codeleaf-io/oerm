package io.codeleaf.oerm.generic;

import io.codeleaf.oerm.SearchCursor;
import io.codeleaf.oerm.SearchCursorAndCount;
import io.codeleaf.oerm.SearchPage;
import io.codeleaf.oerm.SearchPageAndCount;
import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.modeling.selection.SelectionBuilder;

import java.util.Map;
import java.util.Objects;

public final class TypedRepositoryImpl<E, K, D, F, V> implements TypedRepository<E, K, D, F, V> {

    private final Repository<E, K, D, F, V> repository;
    private final D dataType;

    private TypedRepositoryImpl(Repository<E, K, D, F, V> repository, D dataType) {
        this.repository = repository;
        this.dataType = dataType;
    }

    @Override
    public RepositoryTypes<E, K, D, F, V> getGenericTypes() {
        return repository.getGenericTypes();
    }

    @Override
    public D getDataType() {
        return dataType;
    }

    @Override
    public SelectionBuilder<F, V, Selection> getSelectionFactory() {
        return repository.getSelectionFactory();
    }

    @Override
    public K create(E entity) {
        return repository.create(dataType, entity);
    }

    @Override
    public K create(Map<F, V> fields) {
        return repository.create(dataType, fields);
    }

    @Override
    public boolean exists(K entityId) {
        return repository.exists(dataType, entityId);
    }

    @Override
    public E retrieve(K entityId) {
        return repository.retrieve(dataType, entityId);
    }

    @Override
    public E retrieveUnique(Selection selection) {
        return repository.retrieveUnique(dataType, selection);
    }

    @Override
    public boolean isUnique(Selection selection) {
        return repository.isUnique(dataType, selection);
    }

    @Override
    public long count(Selection selection) {
        return repository.count(dataType, selection);
    }

    @Override
    public SearchCursor<E> search(Selection selection) {
        return repository.search(dataType, selection);
    }

    @Override
    public SearchPage<E> search(Selection selection, long offset, int limit) {
        return repository.search(dataType, selection, offset, limit);
    }

    @Override
    public SearchCursorAndCount<E> searchAndCount(Selection selection) {
        return repository.searchAndCount(dataType, selection);
    }

    @Override
    public SearchPageAndCount<E> searchAndCount(Selection selection, long offset, int limit) {
        return repository.searchAndCount(dataType, selection, offset, limit);
    }

    @Override
    public void update(K entityId, E entity) {
        repository.update(dataType, entityId, entity);
    }

    @Override
    public void update(Selection selection, Map<F, V> fields) {
        repository.update(dataType, selection, fields);
    }

    @Override
    public void delete(K entityId) {
        repository.delete(dataType, entityId);
    }

    public static <E, K, D, F, V> TypedRepositoryImpl<E, K, D, F, V> create(Repository<E, K, D, F, V> repository, D dataType) {
        Objects.requireNonNull(repository);
        Objects.requireNonNull(dataType);
        return new TypedRepositoryImpl<>(repository, dataType);
    }
}
