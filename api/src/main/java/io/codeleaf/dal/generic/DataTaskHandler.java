package io.codeleaf.dal.generic;

import io.codeleaf.modeling.task.TaskHandler;

public interface DataTaskHandler<E, K, D, F, V> extends TaskHandler {

    RepositoryTypes<E, K, D, F, V> getGenericTypes();

    DataTaskBuilderPrototypes<E, K, D, F, V> getTaskBuilders();
}
