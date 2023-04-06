package io.codeleaf.oerm.generic;

import io.codeleaf.oerm.generic.tasks.impl.*;

import java.util.Objects;

public final class DataTypeTaskBuilderPrototypes<E, K, D, F, V, S> {

    private final RepositoryTypes<E, K, D, F, V, S> repositoryTypes;

    private DataTypeTaskBuilderPrototypes(RepositoryTypes<E, K, D, F, V, S> repositoryTypes) {
        this.repositoryTypes = repositoryTypes;
    }

    public RepositoryTypes<E, K, D, F, V, S> getRepositoryTypes() {
        return repositoryTypes;
    }

    public ListDataTypesTaskImpl.Builder<D> list() {
        return new ListDataTypesTaskImpl.Builder<>(
                getRepositoryTypes().getDataTypeType());
    }

    public DetermineDataTypeTaskImpl.Builder<E, D> determine() {
        return new DetermineDataTypeTaskImpl.Builder<>(
                getRepositoryTypes().getEntityType(),
                getRepositoryTypes().getDataTypeType());
    }

    public AddDataTypeTaskImpl.Builder<D, S> add() {
        return new AddDataTypeTaskImpl.Builder<>(
                getRepositoryTypes().getDataTypeType(),
                getRepositoryTypes().getEntitySchemaType());
    }

    public GetEntitySchemaTaskImpl.Builder<D, S> get() {
        return new GetEntitySchemaTaskImpl.Builder<>(
                getRepositoryTypes().getDataTypeType(),
                getRepositoryTypes().getEntitySchemaType());
    }

    public RemoveDataTypeTaskImpl.Builder<D, S> remove() {
        return new RemoveDataTypeTaskImpl.Builder<>(
                getRepositoryTypes().getDataTypeType());
    }

    public static <E, K, D, F, V, S> DataTypeTaskBuilderPrototypes<E, K, D, F, V, S> create(RepositoryTypes<E, K, D, F, V, S> repositoryTypes) {
        Objects.requireNonNull(repositoryTypes);
        return new DataTypeTaskBuilderPrototypes<>(repositoryTypes);
    }
}
