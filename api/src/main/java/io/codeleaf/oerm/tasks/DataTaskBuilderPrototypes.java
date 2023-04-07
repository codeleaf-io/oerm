package io.codeleaf.oerm.tasks;

import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.tasks.impl.*;

import java.util.Objects;

public final class DataTaskBuilderPrototypes<E, K, D, F, V, S> {

    private final DataModelTypes<E, K, D, F, V, S> repositoryTypes;
    private final D dataType;

    private DataTaskBuilderPrototypes(DataModelTypes<E, K, D, F, V, S> repositoryTypes, D dataType) {
        this.repositoryTypes = repositoryTypes;
        this.dataType = dataType;
    }

    public static <E, K, D, F, V, S> DataTaskBuilderPrototypes<E, K, D, F, V, S> create(DataModelTypes<E, K, D, F, V, S> repositoryTypes, D dataType) {
        Objects.requireNonNull(repositoryTypes);
        Objects.requireNonNull(dataType);
        return new DataTaskBuilderPrototypes<>(repositoryTypes, dataType);
    }

    public DataModelTypes<E, K, D, F, V, S> getRepositoryTypes() {
        return repositoryTypes;
    }

    public CountTaskImpl.Builder<D, F, V> count() {
        return new CountTaskImpl.Builder<>(
                dataType,
                getRepositoryTypes().getFieldNameType(),
                getRepositoryTypes().getFieldValueType());
    }

    public CreateEntityTaskImpl.Builder<K, D, F, V> createEntity() {
        return new CreateEntityTaskImpl.Builder<>(
                getRepositoryTypes().getEntityIdType(),
                dataType,
                getRepositoryTypes().getFieldNameType(),
                getRepositoryTypes().getFieldValueType());
    }

    public AddEntityTaskImpl.Builder<E, K, D> addEntity() {
        return new AddEntityTaskImpl.Builder<>(
                getRepositoryTypes().getEntityType(),
                getRepositoryTypes().getEntityIdType(),
                dataType);
    }

    public CursorSearchAndCountTaskImpl.Builder<D, F, V, E> cursorSearchAndCount() {
        return new CursorSearchAndCountTaskImpl.Builder<>(
                dataType,
                getRepositoryTypes().getFieldNameType(),
                getRepositoryTypes().getFieldValueType(),
                getRepositoryTypes().getEntityType());
    }

    public CursorSearchTaskImpl.Builder<D, F, V, E> cursorSearch() {
        return new CursorSearchTaskImpl.Builder<>(
                dataType,
                getRepositoryTypes().getFieldNameType(),
                getRepositoryTypes().getFieldValueType(),
                getRepositoryTypes().getEntityType());
    }

    public RemoveTaskImpl.Builder<D, F, V> remove() {
        return new RemoveTaskImpl.Builder<>(
                dataType,
                getRepositoryTypes().getFieldNameType(),
                getRepositoryTypes().getFieldValueType());
    }

    public PageSearchAndCountTaskImpl.Builder<D, F, V, E> pageSearchAndCount() {
        return new PageSearchAndCountTaskImpl.Builder<>(
                dataType,
                getRepositoryTypes().getFieldNameType(),
                getRepositoryTypes().getFieldValueType(),
                getRepositoryTypes().getEntityType());
    }

    public PageSearchTaskImpl.Builder<D, F, V, E> pageSearch() {
        return new PageSearchTaskImpl.Builder<>(
                dataType,
                getRepositoryTypes().getFieldNameType(),
                getRepositoryTypes().getFieldValueType(),
                getRepositoryTypes().getEntityType());
    }

    public RetrieveTaskImpl.Builder<D, F, V, E> retrieve() {
        return new RetrieveTaskImpl.Builder<>(
                dataType,
                getRepositoryTypes().getFieldNameType(),
                getRepositoryTypes().getFieldValueType(),
                getRepositoryTypes().getEntityType());
    }

    public UpdateFieldsTaskImpl.Builder<D, F, V> updateFields() {
        return new UpdateFieldsTaskImpl.Builder<>(
                dataType,
                getRepositoryTypes().getFieldNameType(),
                getRepositoryTypes().getFieldValueType());
    }

    public UpdateEntityTaskImpl.Builder<E, D> updateEntity() {
        return new UpdateEntityTaskImpl.Builder<>(
                getRepositoryTypes().getEntityType(),
                dataType);
    }
}
