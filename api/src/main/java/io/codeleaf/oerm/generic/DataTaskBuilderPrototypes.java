package io.codeleaf.oerm.generic;

import io.codeleaf.oerm.generic.tasks.impl.*;

import java.util.Objects;

public final class DataTaskBuilderPrototypes<E, K, D, F, V, S> {

    private final RepositoryTypes<E, K, D, F, V, S> repositoryTypes;

    private DataTaskBuilderPrototypes(RepositoryTypes<E, K, D, F, V, S> repositoryTypes) {
        this.repositoryTypes = repositoryTypes;
    }

    public RepositoryTypes<E, K, D, F, V, S> getRepositoryTypes() {
        return repositoryTypes;
    }

    public CountTaskImpl.Builder<D, F, V> count() {
        return new CountTaskImpl.Builder<>(
                null,
                getRepositoryTypes().getFieldNameType(),
                getRepositoryTypes().getFieldValueType());
    }

    public CreateEntityTaskImpl.Builder<K, D, F, V> createEntity() {
        return new CreateEntityTaskImpl.Builder<>(
                getRepositoryTypes().getEntityIdType(),
                null,
                getRepositoryTypes().getFieldNameType(),
                getRepositoryTypes().getFieldValueType());
    }

    public AddEntityTaskImpl.Builder<E, K, D> addEntity() {
        return new AddEntityTaskImpl.Builder<>(
                getRepositoryTypes().getEntityType(),
                getRepositoryTypes().getEntityIdType(),
                null);
    }

    public CursorSearchAndCountTaskImpl.Builder<D, F, V, E> cursorSearchAndCount() {
        return new CursorSearchAndCountTaskImpl.Builder<>(
                null,
                getRepositoryTypes().getFieldNameType(),
                getRepositoryTypes().getFieldValueType(),
                getRepositoryTypes().getEntityType());
    }

    public CursorSearchTaskImpl.Builder<D, F, V, E> cursorSearch() {
        return new CursorSearchTaskImpl.Builder<>(
                null,
                getRepositoryTypes().getFieldNameType(),
                getRepositoryTypes().getFieldValueType(),
                getRepositoryTypes().getEntityType());
    }

    public RemoveTaskImpl.Builder<D, F, V> remove() {
        return new RemoveTaskImpl.Builder<>(
                null,
                getRepositoryTypes().getFieldNameType(),
                getRepositoryTypes().getFieldValueType());
    }

    public PageSearchAndCountTaskImpl.Builder<D, F, V, E> pageSearchAndCount() {
        return new PageSearchAndCountTaskImpl.Builder<>(
                null,
                getRepositoryTypes().getFieldNameType(),
                getRepositoryTypes().getFieldValueType(),
                getRepositoryTypes().getEntityType());
    }

    public PageSearchTaskImpl.Builder<D, F, V, E> pageSearch() {
        return new PageSearchTaskImpl.Builder<>(
                null,
                getRepositoryTypes().getFieldNameType(),
                getRepositoryTypes().getFieldValueType(),
                getRepositoryTypes().getEntityType());
    }

    public RetrieveTaskImpl.Builder<D, F, V, E> retrieve() {
        return new RetrieveTaskImpl.Builder<>(
                null,
                getRepositoryTypes().getFieldNameType(),
                getRepositoryTypes().getFieldValueType(),
                getRepositoryTypes().getEntityType());
    }

    public UpdateFieldsTaskImpl.Builder<D, F, V> updateFields() {
        return new UpdateFieldsTaskImpl.Builder<>(
                null,
                getRepositoryTypes().getFieldNameType(),
                getRepositoryTypes().getFieldValueType());
    }

    public UpdateEntityTaskImpl.Builder<E, D> updateEntity() {
        return new UpdateEntityTaskImpl.Builder<>(
                getRepositoryTypes().getEntityType(),
                null);
    }

    public static <E, K, D, F, V, S> DataTaskBuilderPrototypes<E, K, D, F, V, S> create(RepositoryTypes<E, K, D, F, V, S> repositoryTypes) {
        Objects.requireNonNull(repositoryTypes);
        return new DataTaskBuilderPrototypes<>(repositoryTypes);
    }
}
