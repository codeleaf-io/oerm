package io.codeleaf.oerm.entity;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.modeling.task.TaskHandler;
import io.codeleaf.oerm.handlers.CompositeDatabaseTaskHandler;
import io.codeleaf.oerm.tasks.DataTask;
import io.codeleaf.oerm.tasks.DatabaseTask;

import java.util.Map;

public final class EntityDataTaskHandler extends CompositeDatabaseTaskHandler<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema, EntityRepository> {

    public EntityDataTaskHandler(Map<Class<? extends DataTask<?, ?>>, Map<String, TaskHandler>> dataTaskHandlers, Map<Class<? extends DatabaseTask<?>>, TaskHandler> taskHandlers, TaskHandler defaultTaskHandler) {
        super(dataTaskHandlers, taskHandlers, defaultTaskHandler);
    }

    public static final class Builder extends CompositeDatabaseTaskHandler.Builder<
            Builder,
            EntityDataTaskHandler,
            EntityRepository,
            EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

        public Builder() {
            super(EntityRepository.GENERIC_TYPES);
        }

        @Override
        public EntityDataTaskHandler build() {
            return new EntityDataTaskHandler(dataTaskHandlers, taskHandlers, defaultTaskHandler);
        }
    }
}
