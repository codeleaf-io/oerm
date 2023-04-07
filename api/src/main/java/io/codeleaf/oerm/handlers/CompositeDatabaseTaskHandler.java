package io.codeleaf.oerm.handlers;

import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.task.Task;
import io.codeleaf.modeling.task.TaskHandler;
import io.codeleaf.modeling.task.TaskHandlingException;
import io.codeleaf.modeling.task.impl.ScopedTaskHandler;
import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.Repository;
import io.codeleaf.oerm.impl.RepositoryBridge;
import io.codeleaf.oerm.tasks.DataTask;
import io.codeleaf.oerm.tasks.DataTypeTask;
import io.codeleaf.oerm.tasks.DatabaseTask;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public abstract class CompositeDatabaseTaskHandler<E, K, D, F, V, S, R extends Repository<E, K, D, F, V, S>> implements TaskHandler {

    private final Map<Class<? extends DataTask<?, ?>>, Map<D, TaskHandler>> dataTaskHandlers;
    private final Map<Class<? extends DatabaseTask<?>>, TaskHandler> taskHandlers;
    private final TaskHandler defaultTaskHandler;
    private volatile R repository;

    public CompositeDatabaseTaskHandler(Map<Class<? extends DataTask<?, ?>>, Map<D, TaskHandler>> dataTaskHandlers, Map<Class<? extends DatabaseTask<?>>, TaskHandler> taskHandlers, TaskHandler defaultTaskHandler) {
        this.dataTaskHandlers = dataTaskHandlers;
        this.taskHandlers = taskHandlers;
        this.defaultTaskHandler = defaultTaskHandler;
    }

    public final DataModelTypes<E, K, D, F, V, S> getDataModelTypes() {
        return toRepository().getDataModelTypes();
    }

    public final R toRepository() {
        if (repository == null) {
            repository = Types.cast(RepositoryBridge.create(this));
        }
        return repository;
    }

    @Override
    public final <TL extends Task<?>> boolean supportsTaskType(Class<TL> taskType) {
        if (defaultTaskHandler.supportsTaskType(taskType)) {
            return true;
        }
        for (TaskHandler taskHandler : taskHandlers.values()) {
            if (taskHandler.supportsTaskType(taskType)) {
                return true;
            }
        }
        for (Map<D, TaskHandler> taskHandlerMap : dataTaskHandlers.values()) {
            for (TaskHandler taskHandler : taskHandlerMap.values()) {
                if (taskHandler.supportsTaskType(taskType)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public final <OL> OL handleTask(Task<OL> task) throws TaskHandlingException {
        if (task instanceof DataTask<?, ?>) {
            DataTask<?, ?> dataTask = Types.cast(task);
            if (getDataModelTypes().getDataTypeType().isInstance(dataTask.getDataType())) {
                D dataType = Types.cast(dataTask.getDataType());
                Map<D, TaskHandler> taskHandlerMap = dataTaskHandlers.getOrDefault(dataTask.getClass(), Map.of());
                TaskHandler taskHandler = taskHandlerMap.get(dataType);
                if (taskHandler != null) {
                    return taskHandler.handleTask(task);
                }
            }
        }
        return taskHandlers.getOrDefault(task.getClass(), defaultTaskHandler).handleTask(task);
    }

    public static abstract class Builder<
            B extends Builder<B, H, R, E, K, D, F, V, S>,
            H extends CompositeDatabaseTaskHandler<E, K, D, F, V, S, R>,
            R extends Repository<E, K, D, F, V, S>,
            E, K, D, F, V, S> {

        protected final DataModelTypes<E, K, D, F, V, S> dataModelTypes;
        protected final Map<Class<? extends DataTask<?, ?>>, Map<D, TaskHandler>> dataTaskHandlers = new LinkedHashMap<>();
        protected final Map<Class<? extends DatabaseTask<?>>, TaskHandler> taskHandlers = new LinkedHashMap<>();

        protected TaskHandler defaultTaskHandler;

        public Builder(DataModelTypes<E, K, D, F, V, S> dataModelTypes) {
            this.dataModelTypes = dataModelTypes;
        }

        public B withDefaultTaskHandler(Function<Task<?>, ?> function) {
            this.defaultTaskHandler = function == null ? null : ScopedTaskHandler.create(function);
            return Types.cast(this);
        }

        public B withDefaultTaskHandler(TaskHandler taskHandler) {
            this.defaultTaskHandler = taskHandler;
            return Types.cast(this);
        }

        public <T extends DataTypeTask<D, O>, O> B withDataTypeTaskHandler(Class<T> dataTypeTaskType, Function<T, O> function) {
            return withDataTypeTaskHandler(dataTypeTaskType, function == null ? null :
                    new DataTypeTaskHandler.Builder<>(dataModelTypes)
                            .withDataTypeTaskType(dataTypeTaskType)
                            .withFunction(function)
                            .build());
        }

        public <T extends DataTypeTask<D, O>, O> B withDataTypeTaskHandler(Class<T> dataTypeTaskType, TaskHandler taskHandler) {
            if (!DataTypeTask.class.isAssignableFrom(dataTypeTaskType)) {
                throw new IllegalArgumentException();
            }
            setTaskHandler(dataTypeTaskType, taskHandler);
            return Types.cast(this);
        }

        public <T extends DataTask<D, O>, O> B withDataTaskHandler(Class<T> dataTaskType, Function<T, O> function) {
            return withDataTaskHandler(dataTaskType, function == null ? null :
                    new DataTaskHandler.Builder<>(dataModelTypes)
                            .withDataTaskType(dataTaskType)
                            .withFunction(function)
                            .build());
        }

        public <T extends DataTask<D, O>, O> B withDataTaskHandler(Class<T> dataTaskType, TaskHandler taskHandler) {
            if (!DataTask.class.isAssignableFrom(dataTaskType)) {
                throw new IllegalArgumentException();
            }
            if (taskHandler != null && !taskHandler.supportsTaskType(dataTaskType)) {
                throw new IllegalArgumentException();
            }
            setTaskHandler(dataTaskType, taskHandler);
            return Types.cast(this);
        }

        private void setTaskHandler(Class<? extends DatabaseTask<?>> taskType, TaskHandler taskHandler) {
            if (taskHandler != null) {
                taskHandlers.put(taskType, taskHandler);
            } else {
                taskHandlers.remove(taskType);
            }
        }

        public <T extends DataTask<D, O>, O> B withTypedDataTaskHandler(D dataType, Class<T> dataTaskType, Function<T, O> function) {
            return withTypedDataTaskHandler(dataType, dataTaskType, function == null ? null :
                    new DataTaskHandler.Builder<>(dataModelTypes)
                            .withDataType(dataType)
                            .withDataTaskType(dataTaskType)
                            .withFunction(function)
                            .build());
        }

        public <T extends DataTask<D, O>, O> B withTypedDataTaskHandler(D dataType, Class<T> dataTaskType, TaskHandler taskHandler) {
            Objects.requireNonNull(dataType);
            if (!DataTask.class.isAssignableFrom(dataTaskType)) {
                throw new IllegalArgumentException();
            }
            if (taskHandler != null && !taskHandler.supportsTaskType(dataTaskType)) {
                throw new IllegalArgumentException();
            }
            if (taskHandler != null) {
                dataTaskHandlers.computeIfAbsent(dataTaskType, d -> new LinkedHashMap<>()).put(dataType, taskHandler);
            } else {
                dataTaskHandlers.computeIfAbsent(dataTaskType, d -> new LinkedHashMap<>()).remove(dataType);
            }
            return Types.cast(this);
        }

        public abstract H build();
    }
}
