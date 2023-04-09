package io.codeleaf.oerm.impl;

import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.modeling.selection.SelectionBuilder;
import io.codeleaf.modeling.task.TaskHandlingException;
import io.codeleaf.oerm.*;
import io.codeleaf.oerm.tasks.DatabaseTask;
import io.codeleaf.oerm.tasks.data.DataTaskBuilderFactory;
import io.codeleaf.oerm.tasks.meta.MetaTaskBuilderFactory;

import java.util.Map;
import java.util.Set;

public class DefaultRepository<E, K, D, F, V, S> implements Repository<E, K, D, F, V, S> {

    private final DataTaskBuilderFactory<E, K, D, F, V, S> dataTaskBuilderFactory;
    private final MetaTaskBuilderFactory<E, K, D, F, V, S> metaTaskBuilderFactory;
    private final DatabaseTaskHandler<E, K, D, F, V, S> taskHandler;

    public DefaultRepository(DataTaskBuilderFactory<E, K, D, F, V, S> dataTaskBuilderFactory, MetaTaskBuilderFactory<E, K, D, F, V, S> metaTaskBuilderFactory, DatabaseTaskHandler<E, K, D, F, V, S> taskHandler) {
        this.dataTaskBuilderFactory = dataTaskBuilderFactory;
        this.metaTaskBuilderFactory = metaTaskBuilderFactory;
        this.taskHandler = taskHandler;
    }

    public DatabaseTaskHandler<E, K, D, F, V, S> getTaskHandler() {
        return taskHandler;
    }

    @Override
    public DataModelTypes<E, K, D, F, V, S> getDataModelTypes() {
        return taskHandler.getDataModelTypes();
    }

    @Override
    public SelectionBuilder<F, V, Selection> getSelectionFactory() {
        return SelectionBuilder.create(
                taskHandler.getDataModelTypes().getFieldNameType(),
                taskHandler.getDataModelTypes().getFieldValueType());
    }

    @Override
    public Set<D> listSupportedDataTypes() {
        return handleDataTask(metaTaskBuilderFactory.list().build());
    }

    @Override
    public boolean isSupportedDataType(D dataType) {
        return listSupportedDataTypes().contains(dataType);
    }

    @Override
    public D getDataType(E entity) {
        return handleDataTask(metaTaskBuilderFactory.determine().withEntity(entity).build());
    }

    @Override
    public boolean addDataType(D dataType, S schema) {
        return handleDataTask(metaTaskBuilderFactory.add().withDataType(dataType).withSchema(schema).build());
    }

    @Override
    public S getDataSchema(D dataType) {
        return handleDataTask(metaTaskBuilderFactory.get().withDataType(dataType).build());
    }

    @Override
    public Selection select(D dataType, K entityId) {
        return handleDataTask(metaTaskBuilderFactory
                .select()
                .withDataType(dataType)
                .withEntityId(entityId)
                .build());
    }

    @Override
    public K add(D dataType, E entity) {
        return handleDataTask(dataTaskBuilderFactory
                .add()
                .withDataType(dataType)
                .withEntity(entity)
                .build());
    }

    @Override
    public K create(D dataType, Map<F, V> fields) {
        return handleDataTask(dataTaskBuilderFactory
                .create()
                .withDataType(dataType)
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
        return handleDataTask(dataTaskBuilderFactory
                .retrieve()
                .withDataType(dataType)
                .withSelection(selection)
                .build());
    }

    @Override
    public boolean isUnique(D dataType, Selection selection) {
        return count(dataType, selection) == 1;
    }

    @Override
    public long count(D dataType, Selection selection) {
        return handleDataTask(dataTaskBuilderFactory
                .count()
                .withDataType(dataType)
                .withSelection(selection)
                .build()).getCount();
    }

    @Override
    public SearchCursor<E> search(D dataType, Selection selection) {
        return handleDataTask(dataTaskBuilderFactory
                .cursorSearch()
                .withDataType(dataType)
                .withSelection(selection)
                .build());
    }

    @Override
    public SearchPage<E> search(D dataType, Selection selection, long offset, int limit) {
        return handleDataTask(dataTaskBuilderFactory
                .pageSearch()
                .withDataType(dataType)
                .withSelection(selection)
                .withOffset(offset)
                .withLimit(limit)
                .build());
    }

    @Override
    public SearchCursorAndCount<E> searchAndCount(D dataType, Selection selection) {
        return handleDataTask(dataTaskBuilderFactory
                .cursorSearchAndCount()
                .withDataType(dataType)
                .withSelection(selection)
                .build());
    }

    @Override
    public SearchPageAndCount<E> searchAndCount(D dataType, Selection selection, long offset, int limit) {
        return handleDataTask(dataTaskBuilderFactory
                .pageSearchAndCount()
                .withDataType(dataType)
                .withSelection(selection)
                .withOffset(offset)
                .withLimit(limit)
                .build());
    }

    @Override
    public void update(D dataType, E entity) {
        handleDataTask(dataTaskBuilderFactory
                .updateEntity()
                .withDataType(dataType)
                .withEntity(entity)
                .build());
    }

    @Override
    public long update(D dataType, Selection selection, Map<F, V> fields) {
        return handleDataTask(dataTaskBuilderFactory
                .updateFields()
                .withDataType(dataType)
                .withSelection(selection)
                .withFields(fields)
                .build()).getCount();
    }

    @Override
    public void remove(D dataType, K entityId) {
        handleDataTask(dataTaskBuilderFactory
                .remove()
                .withDataType(dataType)
                .withSelection(select(dataType, entityId))
                .build());
    }

    private <O> O handleDataTask(DatabaseTask<E, K, D, F, V, S, O> databaseTask) {
        try {
            return taskHandler.handleTask(databaseTask);
        } catch (TaskHandlingException cause) {
            throw new RepositoryException(this, cause);
        }
    }
}