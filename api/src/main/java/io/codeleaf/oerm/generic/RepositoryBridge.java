package io.codeleaf.oerm.generic;

import io.codeleaf.oerm.SearchCursor;
import io.codeleaf.oerm.SearchCursorAndCount;
import io.codeleaf.oerm.SearchPage;
import io.codeleaf.oerm.SearchPageAndCount;
import io.codeleaf.oerm.generic.tasks.DataTask;
import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.modeling.selection.SelectionBuilder;
import io.codeleaf.modeling.task.TaskHandlingException;

import java.util.Map;
import java.util.Objects;

public final class RepositoryBridge<E, K, D, F, V> implements Repository<E, K, D, F, V> {

    private final RepositoryTypes<E, K, D, F, V> genericTypes;
    private final DataTaskHandler<E, K, D, F, V> dataTaskHandler;
    private final EntityIdSelector<D, K> entityIdSelector;

    private RepositoryBridge(
            RepositoryTypes<E, K, D, F, V> genericTypes,
            DataTaskHandler<E, K, D, F, V> dataTaskHandler,
            EntityIdSelector<D, K> entityIdSelector) {
        this.genericTypes = genericTypes;
        this.dataTaskHandler = dataTaskHandler;
        this.entityIdSelector = entityIdSelector;
    }

    public DataTaskHandler<E, K, D, F, V> getDataTaskHandler() {
        return dataTaskHandler;
    }

    public EntityIdSelector getEntityIdSelector() {
        return entityIdSelector;
    }

    @Override
    public RepositoryTypes<E, K, D, F, V> getGenericTypes() {
        return genericTypes;
    }

    @Override
    public SelectionBuilder<F, V, Selection> getSelectionFactory() {
        return SelectionBuilder.create();
    }

    @Override
    public K create(D dataType, E entity) {
        return handleTask(dataTaskHandler.getTaskBuilders().createEntity()
                .withDataType(dataType)
                .withEntity(entity)
                .build());
    }

    @Override
    public K create(D dataType, Map<F, V> fields) {
        return handleTask(dataTaskHandler.getTaskBuilders().createFields()
                .withDataType(dataType)
                .withFields(fields)
                .build());
    }

    @Override
    public boolean exists(D dataType, K entityId) {
        return count(dataType, entityIdSelector.select(dataType, entityId)) > 0;
    }

    @Override
    public E retrieve(D dataType, K entityId) {
        return retrieveUnique(dataType, entityIdSelector.select(dataType, entityId));
    }

    @Override
    public E retrieveUnique(D dataType, Selection selection) {
        return handleTask(dataTaskHandler.getTaskBuilders().retrieve()
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
        return handleTask(dataTaskHandler.getTaskBuilders().count()
                .withDataType(dataType)
                .withSelection(selection)
                .build()).getCount();
    }

    @Override
    public SearchCursor<E> search(D dataType, Selection selection) {
        return handleTask(dataTaskHandler.getTaskBuilders().cursorSearch()
                .withDataType(dataType)
                .withSelection(selection)
                .build());
    }

    @Override
    public SearchPage<E> search(D dataType, Selection selection, long offset, int limit) {
        return handleTask(dataTaskHandler.getTaskBuilders().pageSearch()
                .withDataType(dataType)
                .withSelection(selection)
                .withOffset(offset)
                .withLimit(limit)
                .build());
    }

    @Override
    public SearchCursorAndCount<E> searchAndCount(D dataType, Selection selection) {
        return handleTask(dataTaskHandler.getTaskBuilders().cursorSearchAndCount()
                .withDataType(dataType)
                .withSelection(selection)
                .build());
    }

    @Override
    public SearchPageAndCount<E> searchAndCount(D dataType, Selection selection, long offset, int limit) {
        return handleTask(dataTaskHandler.getTaskBuilders().pageSearchAndCount()
                .withDataType(dataType)
                .withSelection(selection)
                .withOffset(offset)
                .withLimit(limit)
                .build());
    }

    @Override
    public void update(D dataType, K entityId, E entity) {
        handleTask(dataTaskHandler.getTaskBuilders().updateEntity()
                .withDataType(dataType)
                .withSelection(entityIdSelector.select(dataType, entityId))
                .withEntity(entity)
                .build());
    }

    @Override
    public void update(D dataType, Selection selection, Map<F, V> fields) {
        handleTask(dataTaskHandler.getTaskBuilders().updateFields()
                .withDataType(dataType)
                .withSelection(selection)
                .withFields(fields)
                .build());
    }

    @Override
    public void delete(D dataType, K entityId) {
        handleTask(dataTaskHandler.getTaskBuilders().delete()
                .withDataType(dataType)
                .withSelection(entityIdSelector.select(dataType, entityId))
                .build());
    }

    private <O> O handleTask(DataTask<D, O> dataTask) {
        try {
            return dataTaskHandler.handleTask(dataTask);
        } catch (TaskHandlingException cause) {
            throw new RepositoryException(this, cause);
        }
    }

    public static <E, K, D, F, V> RepositoryBridge<E, K, D, F, V> create(
            RepositoryTypes<E, K, D, F, V> genericTypes,
            DataTaskHandler<E, K, D, F, V> dataTaskHandler,
            EntityIdSelector<D, K> entityIdSelector) {
        Objects.requireNonNull(genericTypes);
        Objects.requireNonNull(dataTaskHandler);
        Objects.requireNonNull(entityIdSelector);
        return new RepositoryBridge<>(genericTypes, dataTaskHandler, entityIdSelector);
    }
}