package io.codeleaf.oerm.dal.impl;

import io.codeleaf.modeling.task.Task;
import io.codeleaf.modeling.task.TaskHandlingException;
import io.codeleaf.oerm.entity.EntityDataTaskHandler;
import io.codeleaf.oerm.generic.tasks.DeleteTask;
import io.codeleaf.oerm.object.ObjectDataTaskHandler;

public final class EntityObjectDataTaskHandler implements ObjectDataTaskHandler {

    private final EntityDataTaskHandler entityTaskHandler;

    public EntityObjectDataTaskHandler(EntityDataTaskHandler entityTaskHandler) {
        this.entityTaskHandler = entityTaskHandler;
    }

    @Override
    public <T extends Task<?>> boolean supportsTaskType(Class<T> taskTypeClass) {
        return entityTaskHandler.supportsTaskType(taskTypeClass);
    }

    @Override
    public <O> O handleTask(Task<O> task) throws TaskHandlingException {
        if (task instanceof DeleteTask) {

        }
        return null;
    }
}
