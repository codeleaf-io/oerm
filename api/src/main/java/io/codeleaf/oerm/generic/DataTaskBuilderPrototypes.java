package io.codeleaf.oerm.generic;

import io.codeleaf.oerm.generic.tasks.impl.*;

import java.util.Objects;

public final class DataTaskBuilderPrototypes<E, K, D, F, V> {

    private final RepositoryTypes<E, K, D, F, V> repositoryTypes;

    private DataTaskBuilderPrototypes(RepositoryTypes<E, K, D, F, V> repositoryTypes) {
        this.repositoryTypes = repositoryTypes;
    }

    public RepositoryTypes<E, K, D, F, V> getRepositoryTypes() {
        return repositoryTypes;
    }

    public CountTaskImpl.Builder<D, F, V> count() {
        return new CountTaskImpl.Builder<>(
                null,
                getRepositoryTypes().getFieldNameType(),
                getRepositoryTypes().getFieldValueType());
    }

    public CreateFieldsTaskImpl.Builder<K, D, F, V> createFields() {
        return new CreateFieldsTaskImpl.Builder<>(
                getRepositoryTypes().getEntityIdType(),
                null,
                getRepositoryTypes().getFieldNameType(),
                getRepositoryTypes().getFieldValueType());
    }

    public CreateEntityTaskImpl.Builder<E, K, D> createEntity() {
        return new CreateEntityTaskImpl.Builder<>(
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

    public DeleteTaskImpl.Builder<D, F, V> delete() {
        return new DeleteTaskImpl.Builder<>(
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

    public UpdateEntityTaskImpl.Builder<E, D, F, V> updateEntity() {
        return new UpdateEntityTaskImpl.Builder<>(
                getRepositoryTypes().getEntityType(),
                null,
                getRepositoryTypes().getFieldNameType(),
                getRepositoryTypes().getFieldValueType());
    }

    public static <E, K, D, F, V> DataTaskBuilderPrototypes<E, K, D, F, V> create(RepositoryTypes<E, K, D, F, V> repositoryTypes) {
        Objects.requireNonNull(repositoryTypes);
        return new DataTaskBuilderPrototypes<>(repositoryTypes);
    }
}
