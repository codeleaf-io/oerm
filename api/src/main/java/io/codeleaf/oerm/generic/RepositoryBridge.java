package io.codeleaf.oerm.generic;

import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.modeling.selection.SelectionBuilder;
import io.codeleaf.modeling.task.TaskHandlingException;
import io.codeleaf.oerm.SearchCursor;
import io.codeleaf.oerm.SearchCursorAndCount;
import io.codeleaf.oerm.SearchPage;
import io.codeleaf.oerm.SearchPageAndCount;
import io.codeleaf.oerm.generic.tasks.DatabaseTask;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public final class RepositoryBridge<E, K, D, F, V, S> implements Repository<E, K, D, F, V, S> {

    private final RepositoryTypes<E, K, D, F, V, S> genericTypes;
    private final DatabaseTaskHandler<E, K, D, F, V, S> taskHandler;

    private RepositoryBridge(
            RepositoryTypes<E, K, D, F, V, S> genericTypes,
            DatabaseTaskHandler<E, K, D, F, V, S> taskHandler) {
        this.genericTypes = genericTypes;
        this.taskHandler = taskHandler;
    }

    public DatabaseTaskHandler<E, K, D, F, V, S> getTaskHandler() {
        return taskHandler;
    }

    @Override
    public RepositoryTypes<E, K, D, F, V, S> getGenericTypes() {
        return genericTypes;
    }

    @Override
    public SelectionBuilder<F, V, Selection> getSelectionFactory() {
        return SelectionBuilder.create();
    }

    @Override
    public Set<D> listSupportedDataTypes() {
        return handleDataTask(taskHandler.getDataTypeTaskBuilders()
                .list()
                .build());
    }

    @Override
    public boolean isSupportedDataType(D dataType) {
        return listSupportedDataTypes().contains(dataType);
    }

    @Override
    public D getDataType(E entity) {
        return handleDataTask(taskHandler.getDataTypeTaskBuilders()
                .determine()
                .withEntity(entity)
                .build());
    }

    @Override
    public S getDataSchema(D dataType) {
        return handleDataTask(taskHandler.getDataTypeTaskBuilders()
                .get()
                .withDataType(dataType)
                .build());
    }

    @Override
    public Selection select(D dataType, K entityId) {
        return handleDataTask(taskHandler.getDataTypeTaskBuilders()
                .select()
                .withDataType(dataType)
                .withEntityId(entityId)
                .build());
    }

    @Override
    public K add(E entity) {
        return handleDataTask(taskHandler.getDataTaskBuilders(getDataType(entity))
                .addEntity()
                .withEntity(entity)
                .build());
    }

    @Override
    public K create(D dataType, Map<F, V> fields) {
        return handleDataTask(taskHandler.getDataTaskBuilders(dataType)
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
        return handleDataTask(taskHandler.getDataTaskBuilders(dataType)
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
        return handleDataTask(taskHandler.getDataTaskBuilders(dataType)
                .count()
                .withSelection(selection)
                .build()).getCount();
    }

    @Override
    public SearchCursor<E> search(D dataType, Selection selection) {
        return handleDataTask(taskHandler.getDataTaskBuilders(dataType)
                .cursorSearch()
                .withSelection(selection)
                .build());
    }

    @Override
    public SearchPage<E> search(D dataType, Selection selection, long offset, int limit) {
        return handleDataTask(taskHandler.getDataTaskBuilders(dataType)
                .pageSearch()
                .withSelection(selection)
                .withOffset(offset)
                .withLimit(limit)
                .build());
    }

    @Override
    public SearchCursorAndCount<E> searchAndCount(D dataType, Selection selection) {
        return handleDataTask(taskHandler.getDataTaskBuilders(dataType)
                .cursorSearchAndCount()
                .withSelection(selection)
                .build());
    }

    @Override
    public SearchPageAndCount<E> searchAndCount(D dataType, Selection selection, long offset, int limit) {
        return handleDataTask(taskHandler.getDataTaskBuilders(dataType)
                .pageSearchAndCount()
                .withSelection(selection)
                .withOffset(offset)
                .withLimit(limit)
                .build());
    }

    @Override
    public void update(E entity) {
        handleDataTask(taskHandler.getDataTaskBuilders(getDataType(entity))
                .updateEntity()
                .withEntity(entity)
                .build());
    }

    @Override
    public void update(D dataType, Selection selection, Map<F, V> fields) {
        handleDataTask(taskHandler.getDataTaskBuilders(dataType)
                .updateFields()
                .withSelection(selection)
                .withFields(fields)
                .build());
    }

    @Override
    public void remove(D dataType, K entityId) {
        handleDataTask(taskHandler.getDataTaskBuilders(dataType)
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

    public static <E, K, D, F, V, S> RepositoryBridge<E, K, D, F, V, S> create(
            RepositoryTypes<E, K, D, F, V, S> genericTypes,
            DatabaseTaskHandler<E, K, D, F, V, S> dataTaskHandler) {
        Objects.requireNonNull(genericTypes);
        Objects.requireNonNull(dataTaskHandler);
        return new RepositoryBridge<>(genericTypes, new DelegatingDataTaskHandler<>(dataTaskHandler));
    }
}