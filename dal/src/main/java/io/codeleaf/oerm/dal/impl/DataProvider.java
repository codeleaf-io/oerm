package io.codeleaf.oerm.dal.impl;

import io.codeleaf.modeling.task.Task;
import io.codeleaf.modeling.task.TaskHandlingException;
import io.codeleaf.oerm.entity.EntityDataTaskHandler;
import io.codeleaf.oerm.record.RecordDataTaskHandler;

public final class DataProvider implements EntityDataTaskHandler {

    private final RecordDataTaskHandler handler;

    public DataProvider(RecordDataTaskHandler handler) {
        this.handler = handler;
    }

    @Override
    public <T extends Task<?>> boolean supportsTaskType(Class<T> taskTypeClass) {
        return handler.supportsTaskType(taskTypeClass);
    }

    @Override
    public <O> O handleTask(Task<O> task) throws TaskHandlingException {
        return handler.handleTask(task);
    }
}
