package io.codeleaf.dal;

import io.codeleaf.dal.tasks.impl.*;

import java.util.Objects;

public final class TaskBuilderFactory<E, K, D, F, V, H> {

    private final RepositoryType<E, K, D, F, V, H> repositoryType;

    private TaskBuilderFactory(RepositoryType<E, K, D, F, V, H> repositoryType) {
        this.repositoryType = repositoryType;
    }

    public RepositoryType<E, K, D, F, V, H> getRepositoryType() {
        return repositoryType;
    }

    public CountTaskImpl.Builder<D, F, V> count() {
        return new CountTaskImpl.Builder<>(
                null,
                getRepositoryType().getFieldNameType(),
                getRepositoryType().getFieldValueType());
    }

    public CreateFieldsTaskImpl.Builder<K, D, F, V> createFields() {
        return new CreateFieldsTaskImpl.Builder<>(
                getRepositoryType().getObjectIdType(),
                null,
                getRepositoryType().getFieldNameType(),
                getRepositoryType().getFieldValueType());
    }

    public CreateObjectTaskImpl.Builder<E, K, D> createObject() {
        return new CreateObjectTaskImpl.Builder<>(
                getRepositoryType().getObjectType(),
                getRepositoryType().getObjectIdType(),
                null);
    }

    public CursorSearchAndCountTaskImpl.Builder<D, F, V, H> cursorSearchAndCount() {
        return new CursorSearchAndCountTaskImpl.Builder<>(
                null,
                getRepositoryType().getFieldNameType(),
                getRepositoryType().getFieldValueType(),
                getRepositoryType().getSearchHitType());
    }

    public CursorSearchTaskImpl.Builder<D, F, V, H> cursorSearch() {
        return new CursorSearchTaskImpl.Builder<>(
                null,
                getRepositoryType().getFieldNameType(),
                getRepositoryType().getFieldValueType(),
                getRepositoryType().getSearchHitType());
    }

    public DeleteTaskImpl.Builder<D, F, V> delete() {
        return new DeleteTaskImpl.Builder<>(
                null,
                getRepositoryType().getFieldNameType(),
                getRepositoryType().getFieldValueType());
    }

    public PageSearchAndCountTaskImpl.Builder<D, F, V, H> pageSearchAndCount() {
        return new PageSearchAndCountTaskImpl.Builder<>(
                null,
                getRepositoryType().getFieldNameType(),
                getRepositoryType().getFieldValueType(),
                getRepositoryType().getSearchHitType());
    }

    public PageSearchTaskImpl.Builder<D, F, V, H> pageSearch() {
        return new PageSearchTaskImpl.Builder<>(
                null,
                getRepositoryType().getFieldNameType(),
                getRepositoryType().getFieldValueType(),
                getRepositoryType().getSearchHitType());
    }

    public RetrieveTaskImpl.Builder<D, F, V, H> retrieve() {
        return new RetrieveTaskImpl.Builder<>(
                null,
                getRepositoryType().getFieldNameType(),
                getRepositoryType().getFieldValueType(),
                getRepositoryType().getSearchHitType());
    }

    public UpdateFieldsTaskImpl.Builder<D, F, V> updateFields() {
        return new UpdateFieldsTaskImpl.Builder<>(
                null,
                getRepositoryType().getFieldNameType(),
                getRepositoryType().getFieldValueType());
    }

    public UpdateObjectTaskImpl.Builder<E, D, F, V> updateObject() {
        return new UpdateObjectTaskImpl.Builder<>(
                getRepositoryType().getObjectType(),
                null,
                getRepositoryType().getFieldNameType(),
                getRepositoryType().getFieldValueType());
    }

    public static <E, K, D, F, V, H> TaskBuilderFactory<E, K, D, F, V, H> create(RepositoryType<E, K, D, F, V, H> repositoryType) {
        Objects.requireNonNull(repositoryType);
        return new TaskBuilderFactory<>(repositoryType);
    }
}
