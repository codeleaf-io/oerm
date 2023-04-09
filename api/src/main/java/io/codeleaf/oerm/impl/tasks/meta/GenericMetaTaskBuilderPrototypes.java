package io.codeleaf.oerm.impl.tasks.meta;

import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.tasks.meta.MetaTaskBuilderFactory;

public final class GenericMetaTaskBuilderPrototypes<E, K, D, F, V, S> implements MetaTaskBuilderFactory<E, K, D, F, V, S> {

    private final DataModelTypes<E, K, D, F, V, S> dataModelTypes;

    public GenericMetaTaskBuilderPrototypes(DataModelTypes<E, K, D, F, V, S> dataModelTypes) {
        this.dataModelTypes = dataModelTypes;
    }

    @Override
    public DataModelTypes<E, K, D, F, V, S> getDataModelTypes() {
        return dataModelTypes;
    }

    @Override
    public GenericSelectEntityTask.Builder<E, K, D, F, V, S> select() {
        return new GenericSelectEntityTask.Builder<>(dataModelTypes);
    }

    @Override
    public GenericGetDataTypesTask.Builder<E, K, D, F, V, S> list() {
        return new GenericGetDataTypesTask.Builder<>(dataModelTypes);
    }

    @Override
    public GenericDetermineDataTypeTask.Builder<E, K, D, F, V, S> determine() {
        return new GenericDetermineDataTypeTask.Builder<>(dataModelTypes);
    }

    @Override
    public GenericAddDataTypeTask.Builder<E, K, D, F, V, S> add() {
        return new GenericAddDataTypeTask.Builder<>(dataModelTypes);
    }

    @Override
    public GenericGetEntitySchemaTask.Builder<E, K, D, F, V, S> get() {
        return new GenericGetEntitySchemaTask.Builder<>(dataModelTypes);
    }

    @Override
    public GenericRemoveDataTypeTask.Builder<E, K, D, F, V, S> remove() {
        return new GenericRemoveDataTypeTask.Builder<>(dataModelTypes);
    }
}
