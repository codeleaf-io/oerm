package io.codeleaf.oerm.impl;

import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.modeling.selection.SelectionBuilder;
import io.codeleaf.oerm.*;
import io.codeleaf.oerm.Repository;
import io.codeleaf.oerm.TypedRepository;

import java.util.Map;
import java.util.Objects;

public final class TypedRepositoryImpl<E, K, D, F, V, S> implements TypedRepository<E, K, D, F, V, S> {

    private final Repository<E, K, D, F, V, S> repository;
    private final D dataType;

    private TypedRepositoryImpl(Repository<E, K, D, F, V, S> repository, D dataType) {
        this.repository = repository;
        this.dataType = dataType;
    }

    public static <E, K, D, F, V, S> TypedRepositoryImpl<E, K, D, F, V, S> create(Repository<E, K, D, F, V, S> repository, D dataType) {
        Objects.requireNonNull(repository);
        Objects.requireNonNull(dataType);
        return new TypedRepositoryImpl<>(repository, dataType);
    }

    @Override
    public DataModelTypes<E, K, D, F, V, S> getDataModelTypes() {
        return repository.getDataModelTypes();
    }

    @Override
    public D getDataType() {
        return dataType;
    }

    @Override
    public S getDataSchema() {
        return repository.getDataSchema(dataType);
    }

    @Override
    public Selection select(K entityId) {
        return repository.select(dataType, entityId);
    }

    @Override
    public SelectionBuilder<F, V, Selection> getSelectionFactory() {
        return repository.getSelectionFactory();
    }

    @Override
    public K create(E entity) {
        return repository.add(dataType, entity);
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
    public E get(K entityId) {
        return repository.get(dataType, entityId);
    }

    @Override
    public E retrieve(Selection selection) {
        return repository.retrieve(dataType, selection);
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
    public void update(E entity) {
        repository.update(dataType, entity);
    }

    @Override
    public long update(Selection selection, Map<F, V> fields) {
        return repository.update(dataType, selection, fields);
    }

    @Override
    public void delete(K entityId) {
        repository.remove(dataType, entityId);
    }
}
