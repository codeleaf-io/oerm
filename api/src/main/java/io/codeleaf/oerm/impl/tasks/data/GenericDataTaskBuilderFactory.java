package io.codeleaf.oerm.impl.tasks.data;

import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.tasks.data.DataTaskBuilderFactory;

public final class GenericDataTaskBuilderFactory<E, K, D, F, V, S> implements DataTaskBuilderFactory<E, K, D, F, V, S> {

    private final DataModelTypes<E, K, D, F, V, S> dataModelTypes;

    public GenericDataTaskBuilderFactory(DataModelTypes<E, K, D, F, V, S> dataModelTypes) {
        this.dataModelTypes = dataModelTypes;
    }

    @Override
    public DataModelTypes<E, K, D, F, V, S> getDataModelTypes() {
        return dataModelTypes;
    }

    @Override
    public GenericCountEntitiesTask.Builder<E, K, D, F, V, S> count() {
        return new GenericCountEntitiesTask.Builder<>(dataModelTypes);
    }

    @Override
    public GenericCreateEntityTask.Builder<E, K, D, F, V, S> create() {
        return new GenericCreateEntityTask.Builder<>(dataModelTypes);
    }

    @Override
    public GenericAddEntityTask.Builder<E, K, D, F, V, S> add() {
        return new GenericAddEntityTask.Builder<>(dataModelTypes);
    }

    @Override
    public GenericCursorSearchAndCountTask.Builder<E, K, D, F, V, S> cursorSearchAndCount() {
        return new GenericCursorSearchAndCountTask.Builder<>(dataModelTypes);
    }

    @Override
    public GenericCursorSearchTask.Builder<E, K, D, F, V, S> cursorSearch() {
        return new GenericCursorSearchTask.Builder<>(dataModelTypes);
    }

    @Override
    public GenericRemoveEntitiesTask.Builder<E, K, D, F, V, S> remove() {
        return new GenericRemoveEntitiesTask.Builder<>(dataModelTypes);
    }

    @Override
    public GenericPageSearchAndCountTask.Builder<E, K, D, F, V, S> pageSearchAndCount() {
        return new GenericPageSearchAndCountTask.Builder<>(dataModelTypes);
    }

    @Override
    public GenericPageSearchTask.Builder<E, K, D, F, V, S> pageSearch() {
        return new GenericPageSearchTask.Builder<>(dataModelTypes);
    }

    @Override
    public GenericRetrieveEntityTask.Builder<E, K, D, F, V, S> retrieve() {
        return new GenericRetrieveEntityTask.Builder<>(dataModelTypes);
    }

    @Override
    public GenericUpdateFieldsTask.Builder<E, K, D, F, V, S> updateFields() {
        return new GenericUpdateFieldsTask.Builder<>(dataModelTypes);
    }

    @Override
    public GenericUpdateEntityTask.Builder<E, K, D, F, V, S> updateEntity() {
        return new GenericUpdateEntityTask.Builder<>(dataModelTypes);
    }
}
