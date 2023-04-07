package io.codeleaf.oerm.impl;

import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.modeling.selection.SelectionBuilder;
import io.codeleaf.modeling.task.TaskHandlingException;
import io.codeleaf.oerm.*;
import io.codeleaf.oerm.handlers.CompositeDatabaseTaskHandler;
import io.codeleaf.oerm.tasks.DataTaskBuilderPrototypes;
import io.codeleaf.oerm.tasks.DataTypeTaskBuilderPrototypes;
import io.codeleaf.oerm.tasks.DatabaseTask;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class RepositoryBridge<E, K, D, F, V, S, R extends Repository<E, K, D, F, V, S>> implements Repository<E, K, D, F, V, S> {

    private final Map<D, DataTaskBuilderPrototypes<E, K, D, F, V, S>> dataTaskBuilderPrototypes = new ConcurrentHashMap<>();
    private final DataTypeTaskBuilderPrototypes<E, K, D, F, V, S> dataTypeTaskBuilderPrototypes;
    private final CompositeDatabaseTaskHandler<E, K, D, F, V, S, R> taskHandler;

    private RepositoryBridge(CompositeDatabaseTaskHandler<E, K, D, F, V, S, R> taskHandler) {
        this.taskHandler = taskHandler;
        dataTypeTaskBuilderPrototypes = DataTypeTaskBuilderPrototypes.create(taskHandler.getDataModelTypes());
    }

    public static <E, K, D, F, V, S, R extends Repository<E, K, D, F, V, S>> RepositoryBridge<E, K, D, F, V, S, R> create(
            CompositeDatabaseTaskHandler<E, K, D, F, V, S, R> dataTaskHandler) {
        Objects.requireNonNull(dataTaskHandler);
        return new RepositoryBridge<>(dataTaskHandler);
    }

    @Override
    public DataModelTypes<E, K, D, F, V, S> getDataModelTypes() {
        return taskHandler.getDataModelTypes();
    }

    @Override
    public SelectionBuilder<F, V, Selection> getSelectionFactory() {
        return SelectionBuilder.create();
    }

    private DataTaskBuilderPrototypes<E, K, D, F, V, S> getDataTaskBuilders(D dataType) {
        return dataTaskBuilderPrototypes.computeIfAbsent(dataType,
                d -> DataTaskBuilderPrototypes.create(getDataModelTypes(), d));
    }

    private DataTypeTaskBuilderPrototypes<E, K, D, F, V, S> getDataTypeTaskBuilders() {
        return dataTypeTaskBuilderPrototypes;
    }

    @Override
    public Set<D> listSupportedDataTypes() {
        return handleDataTask(getDataTypeTaskBuilders()
                .list()
                .build());
    }

    @Override
    public boolean isSupportedDataType(D dataType) {
        return listSupportedDataTypes().contains(dataType);
    }

    @Override
    public D getDataType(E entity) {
        return handleDataTask(getDataTypeTaskBuilders()
                .determine()
                .withEntity(entity)
                .build());
    }

    @Override
    public S getDataSchema(D dataType) {
        return handleDataTask(getDataTypeTaskBuilders()
                .get()
                .withDataType(dataType)
                .build());
    }

    @Override
    public Selection select(D dataType, K entityId) {
        return handleDataTask(getDataTypeTaskBuilders()
                .select()
                .withDataType(dataType)
                .withEntityId(entityId)
                .build());
    }

    @Override
    public K add(E entity) {
        return handleDataTask(getDataTaskBuilders(getDataType(entity))
                .addEntity()
                .withEntity(entity)
                .build());
    }

    @Override
    public K create(D dataType, Map<F, V> fields) {
        return handleDataTask(getDataTaskBuilders(dataType)
                .createEntity()
                .withFields(fields)
                .build());
    }

    @Override
    public boolean exists(D dataType, K entityId) {
        return count(dataType, select(dataType, entityId)) > 0;
    }

    @Override
    public E get(D dataType, K entityId) {
        return retrieve(dataType, select(dataType, entityId));
    }

    @Override
    public E retrieve(D dataType, Selection selection) {
        return handleDataTask(getDataTaskBuilders(dataType)
                .retrieve()
                .withSelection(selection)
                .build());
    }

    @Override
    public boolean isUnique(D dataType, Selection selection) {
        return count(dataType, selection) == 1;
    }

    @Override
    public long count(D dataType, Selection selection) {
        return handleDataTask(getDataTaskBuilders(dataType)
                .count()
                .withSelection(selection)
                .build()).getCount();
    }

    @Override
    public SearchCursor<E> search(D dataType, Selection selection) {
        return handleDataTask(getDataTaskBuilders(dataType)
                .cursorSearch()
                .withSelection(selection)
                .build());
    }

    @Override
    public SearchPage<E> search(D dataType, Selection selection, long offset, int limit) {
        return handleDataTask(getDataTaskBuilders(dataType)
                .pageSearch()
                .withSelection(selection)
                .withOffset(offset)
                .withLimit(limit)
                .build());
    }

    @Override
    public SearchCursorAndCount<E> searchAndCount(D dataType, Selection selection) {
        return handleDataTask(getDataTaskBuilders(dataType)
                .cursorSearchAndCount()
                .withSelection(selection)
                .build());
    }

    @Override
    public SearchPageAndCount<E> searchAndCount(D dataType, Selection selection, long offset, int limit) {
        return handleDataTask(getDataTaskBuilders(dataType)
                .pageSearchAndCount()
                .withSelection(selection)
                .withOffset(offset)
                .withLimit(limit)
                .build());
    }

    @Override
    public void update(E entity) {
        handleDataTask(getDataTaskBuilders(getDataType(entity))
                .updateEntity()
                .withEntity(entity)
                .build());
    }

    @Override
    public long update(D dataType, Selection selection, Map<F, V> fields) {
        return handleDataTask(getDataTaskBuilders(dataType)
                .updateFields()
                .withSelection(selection)
                .withFields(fields)
                .build()).getCount();
    }

    @Override
    public void remove(D dataType, K entityId) {
        handleDataTask(getDataTaskBuilders(dataType)
                .remove()
                .withSelection(select(dataType, entityId))
                .build());
    }

    private <O> O handleDataTask(DatabaseTask<O> databaseTask) {
        try {
            return taskHandler.handleTask(databaseTask);
        } catch (TaskHandlingException cause) {
            throw new RepositoryException(this, cause);
        }
    }
}