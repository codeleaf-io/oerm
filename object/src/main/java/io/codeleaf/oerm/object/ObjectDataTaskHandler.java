package io.codeleaf.oerm.object;

import io.codeleaf.common.utils.MethodReferences;
import io.codeleaf.modeling.task.TaskHandler;
import io.codeleaf.oerm.handlers.CompositeDatabaseTaskHandler;
import io.codeleaf.oerm.tasks.DataTask;
import io.codeleaf.oerm.tasks.DatabaseTask;

import java.util.Map;
import java.util.function.Supplier;

public final class ObjectDataTaskHandler extends CompositeDatabaseTaskHandler<Entity, Reference<Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>, ObjectRepository> {

    public ObjectDataTaskHandler(Map<Class<? extends DataTask<?, ?>>, Map<Class<? extends Entity>, TaskHandler>> dataTaskHandlers, Map<Class<? extends DatabaseTask<?>>, TaskHandler> taskHandlers, TaskHandler defaultTaskHandler) {
        super(dataTaskHandlers, taskHandlers, defaultTaskHandler);
    }

    public <D extends Entity> D getFieldNames(Class<D> entityType) {
        return MethodReferences.createProxy(entityType);
    }

    public static final class Builder extends CompositeDatabaseTaskHandler.Builder<
            Builder,
            ObjectDataTaskHandler,
            ObjectRepository,
            Entity, Reference<Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {

        public Builder() {
            super(ObjectRepository.GENERIC_TYPES);
        }

        @Override
        public ObjectDataTaskHandler build() {
            return new ObjectDataTaskHandler(dataTaskHandlers, taskHandlers, defaultTaskHandler);
        }
    }
}
