package io.codeleaf.dal.tasks;

import io.codeleaf.dal.types.Entity;
import io.codeleaf.modeling.task.Task;

public interface EntityTask<E extends Entity, O> extends Task<O> {

    Class<E> getEntityType();

}
