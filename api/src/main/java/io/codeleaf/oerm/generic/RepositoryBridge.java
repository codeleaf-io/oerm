package io.codeleaf.oerm.generic;

import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.modeling.selection.SelectionBuilder;
import io.codeleaf.modeling.task.TaskHandlingException;
import io.codeleaf.oerm.SearchCursor;
import io.codeleaf.oerm.SearchCursorAndCount;
import io.codeleaf.oerm.SearchPage;
import io.codeleaf.oerm.SearchPageAndCount;
import io.codeleaf.oerm.generic.tasks.DataTask;

import java.util.Map;
import java.util.Objects;
import java.util.Set;

public final class RepositoryBridge<E, K, D, F, V, S> implements Repository<E, K, D, F, V, S> {

    private final RepositoryTypes<E, K, D, F, V, S> genericTypes;
    private final DataTaskHandler<E, K, D, F, V, S> dataTaskHandler;

    private RepositoryBridge(
            RepositoryTypes<E, K, D, F, V, S> genericTypes,
            DataTaskHandler<E, K, D, F, V, S> dataTaskHandler) {
        this.genericTypes = genericTypes;
        this.dataTaskHandler = dataTaskHandler;
    }

    public DataTaskHandler<E, K, D, F, V, S> getDataTaskHandler() {
        return dataTaskHandler;
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
        return null;
    }

    @Override
    public boolean isSupportedDataType(D dataType) {
        return false;
    }

    @Override
    public D getDataType(E entity) {
        return null;
    }

    @Override
    public S getDataSchema(D dataType) {
        return null;
    }

    @Override
    public Selection select(D dataType, K entityId) {
        return null;
    }

    @Override
    public K add(E entity) {
        return handleTask(dataTaskHandler.getTaskBuilders(getDataType(entity))
                .addEntity()
                .withEntity(entity)
                .build());
    }

    @Override
    public K create(D dataType, Map<F, V> fields) {
        return handleTask(dataTaskHandler.getTaskBuilders(dataType)
                .createEntity()
                .withFields(fields)
                .build());
    }

    @Override
    public boolean exists(D dataType, K entityId) {
        return count(dataType, select(dataType, entityId)) > 0;
    }

    @Override
    public E retrieve(D dataType, K entityId) {
        return retrieveUnique(dataType, select(dataType, entityId));
    }

    @Override
    public E retrieveUnique(D dataType, Selection selection) {
        return handleTask(dataTaskHandler.getTaskBuilders(dataType)
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
        return handleTask(dataTaskHandler.getTaskBuilders(dataType)
                .count()
                .withSelection(selection)
                .build()).getCount();
    }

    @Override
    public SearchCursor<E> search(D dataType, Selection selection) {
        return handleTask(dataTaskHandler.getTaskBuilders(dataType)
                .cursorSearch()
                .withSelection(selection)
                .build());
    }

    @Override
    public SearchPage<E> search(D dataType, Selection selection, long offset, int limit) {
        return handleTask(dataTaskHandler.getTaskBuilders(dataType)
                .pageSearch()
                .withSelection(selection)
                .withOffset(offset)
                .withLimit(limit)
                .build());
    }

    @Override
    public SearchCursorAndCount<E> searchAndCount(D dataType, Selection selection) {
        return handleTask(dataTaskHandler.getTaskBuilders(dataType)
                .cursorSearchAndCount()
                .withSelection(selection)
                .build());
    }

    @Override
    public SearchPageAndCount<E> searchAndCount(D dataType, Selection selection, long offset, int limit) {
        return handleTask(dataTaskHandler.getTaskBuilders(dataType)
                .pageSearchAndCount()
                .withSelection(selection)
                .withOffset(offset)
                .withLimit(limit)
                .build());
    }

    @Override
    public void update(E entity) {
        handleTask(dataTaskHandler.getTaskBuilders(getDataType(entity))
                .updateEntity()
                .withEntity(entity)
                .build());
    }

    @Override
    public void update(D dataType, Selection selection, Map<F, V> fields) {
        handleTask(dataTaskHandler.getTaskBuilders(dataType)
                .updateFields()
                .withSelection(selection)
                .withFields(fields)
                .build());
    }

    @Override
    public void remove(D dataType, K entityId) {
        handleTask(dataTaskHandler.getTaskBuilders(dataType)
                .remove()
                .withSelection(select(dataType, entityId))
                .build());
    }

    private <O> O handleTask(DataTask<D, O> dataTask) {
        try {
            return dataTaskHandler.handleTask(dataTask);
        } catch (TaskHandlingException cause) {
            throw new RepositoryException(this, cause);
        }
    }

    public static <E, K, D, F, V, S> RepositoryBridge<E, K, D, F, V, S> create(
            RepositoryTypes<E, K, D, F, V, S> genericTypes,
            DataTaskHandler<E, K, D, F, V, S> dataTaskHandler) {
        Objects.requireNonNull(genericTypes);
        Objects.requireNonNull(dataTaskHandler);
        return new RepositoryBridge<>(genericTypes, new DelegatingDataTaskHandler<>(dataTaskHandler));
    }
}