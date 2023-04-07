package io.codeleaf.oerm.tasks;

import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.tasks.impl.*;

import java.util.Objects;

public final class DataTypeTaskBuilderPrototypes<E, K, D, F, V, S> {

    private final DataModelTypes<E, K, D, F, V, S> repositoryTypes;

    private DataTypeTaskBuilderPrototypes(DataModelTypes<E, K, D, F, V, S> repositoryTypes) {
        this.repositoryTypes = repositoryTypes;
    }

    public static <E, K, D, F, V, S> DataTypeTaskBuilderPrototypes<E, K, D, F, V, S> create(DataModelTypes<E, K, D, F, V, S> repositoryTypes) {
        Objects.requireNonNull(repositoryTypes);
        return new DataTypeTaskBuilderPrototypes<>(repositoryTypes);
    }

    public DataModelTypes<E, K, D, F, V, S> getRepositoryTypes() {
        return repositoryTypes;
    }

    public SelectEntityTaskImpl.Builder<K, D> select() {
        return new SelectEntityTaskImpl.Builder<>(
                getRepositoryTypes().getEntityIdType(),
                getRepositoryTypes().getDataTypeType());
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
}
