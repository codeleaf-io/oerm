package io.codeleaf.oerm.generic;

import io.codeleaf.modeling.task.TaskHandler;

public interface DatabaseTaskHandler<E, K, D, F, V, S> extends TaskHandler {

    RepositoryTypes<E, K, D, F, V, S> getGenericTypes();

    default DataTaskBuilderPrototypes<E, K, D, F, V, S> getDataTaskBuilders(D dataType) {
        return DataTaskBuilderPrototypes.create(getGenericTypes(), dataType);
    }

    default DataTypeTaskBuilderPrototypes<E, K, D, F, V, S> getDataTypeTaskBuilders() {
        return DataTypeTaskBuilderPrototypes.create(getGenericTypes());
    }
}
