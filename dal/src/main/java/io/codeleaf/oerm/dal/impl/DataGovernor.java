package io.codeleaf.oerm.dal.impl;

import io.codeleaf.modeling.task.Task;
import io.codeleaf.modeling.task.TaskHandlingException;
import io.codeleaf.oerm.entity.EntityDataTaskHandler;

public final class DataGovernor implements EntityDataTaskHandler {

    private final DataProvider provider;

    public DataGovernor(DataProvider provider) {
        this.provider = provider;
    }

    @Override
    public <T extends Task<?>> boolean supportsTaskType(Class<T> taskTypeClass) {
        return provider.supportsTaskType(taskTypeClass);
    }

    @Override
    public <O> O handleTask(Task<O> task) throws TaskHandlingException {
        return provider.handleTask(task);
    }
}
