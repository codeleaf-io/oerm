package io.codeleaf.oerm.generic;

import io.codeleaf.modeling.task.TaskHandler;

public interface DataTaskHandler<E, K, D, F, V, S> extends TaskHandler {

    RepositoryTypes<E, K, D, F, V, S> getGenericTypes();

    default DataTaskBuilderPrototypes<E, K, D, F, V, S> getTaskBuilders() {
        return DataTaskBuilderPrototypes.create(getGenericTypes());
    }
}
