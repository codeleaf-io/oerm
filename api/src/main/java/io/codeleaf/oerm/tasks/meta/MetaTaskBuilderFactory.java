package io.codeleaf.oerm.tasks.meta;

import io.codeleaf.oerm.DataModelTypes;

public interface MetaTaskBuilderFactory<E, K, D, F, V, S> {

    DataModelTypes<E, K, D, F, V, S> getDataModelTypes();

    SelectEntityTask.Builder<?, ?, E, K, D, F, V, S> select();

    DetermineDataTypeTask.Builder<?, ?, E, K, D, F, V, S> determine();

    GetDataTypesTask.Builder<?, ?, E, K, D, F, V, S> list();

    AddDataTypeTask.Builder<?, ?, E, K, D, F, V, S> add();

    GetEntitySchemaTask.Builder<?, ?, E, K, D, F, V, S> get();

    RemoveDataTypeTask.Builder<?, ?, E, K, D, F, V, S> remove();

}
