package io.codeleaf.oerm.impl;

import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.task.Task;
import io.codeleaf.modeling.task.TaskHandler;
import io.codeleaf.modeling.task.TaskHandlingException;
import io.codeleaf.modeling.task.impl.FunctionTaskHandler;
import io.codeleaf.modeling.task.impl.ScopedTaskHandler;
import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.tasks.DatabaseTask;
import io.codeleaf.oerm.tasks.TypeBasedTask;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

public final class DatabaseTaskHandler<E, K, D, F, V, S> implements TaskHandler {

    private final DataModelTypes<E, K, D, F, V, S> dataModelTypes;
    private final Map<Class<? extends TypeBasedTask<E, K, D, F, V, S, ?>>, Map<D, TaskHandler>> typeBasedTaskHandlers;
    private final Map<Class<? extends DatabaseTask<E, K, D, F, V, S, ?>>, TaskHandler> taskHandlers;
    private final TaskHandler defaultTaskHandler;

    public DatabaseTaskHandler(DataModelTypes<E, K, D, F, V, S> dataModelTypes, Map<Class<? extends TypeBasedTask<E, K, D, F, V, S, ?>>, Map<D, TaskHandler>> typeBasedTaskHandlers, Map<Class<? extends DatabaseTask<E, K, D, F, V, S, ?>>, TaskHandler> taskHandlers, TaskHandler defaultTaskHandler) {
        this.dataModelTypes = dataModelTypes;
        this.typeBasedTaskHandlers = typeBasedTaskHandlers;
        this.taskHandlers = taskHandlers;
        this.defaultTaskHandler = defaultTaskHandler;
    }

    public DataModelTypes<E, K, D, F, V, S> getDataModelTypes() {
        return dataModelTypes;
    }

    @Override
    public <TL extends Task<?>> boolean supportsTaskType(Class<TL> taskType) {
        if (defaultTaskHandler.supportsTaskType(taskType)) {
            return true;
        }
        for (TaskHandler taskHandler : taskHandlers.values()) {
            if (taskHandler.supportsTaskType(taskType)) {
                return true;
            }
        }
        for (Map<D, TaskHandler> taskHandlerMap : typeBasedTaskHandlers.values()) {
            for (TaskHandler taskHandler : taskHandlerMap.values()) {
                if (taskHandler.supportsTaskType(taskType)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public <OL> OL handleTask(Task<OL> task) throws TaskHandlingException {
        if (task instanceof TypeBasedTask<?, ?, ?, ?, ?, ?, ?>) {
            TypeBasedTask<E, K, D, F, V, S, ?> dataTask = Types.cast(task);
            if (getDataModelTypes().getDataTypeType().isInstance(dataTask.getDataType())) {
                D dataType = Types.cast(dataTask.getDataType());
                Map<D, TaskHandler> taskHandlerMap = typeBasedTaskHandlers.getOrDefault(dataTask.getClass(), Map.of());
                TaskHandler taskHandler = taskHandlerMap.get(dataType);
                if (taskHandler != null) {
                    return taskHandler.handleTask(task);
                }
            }
        }
        return taskHandlers.getOrDefault(task.getClass(), defaultTaskHandler).handleTask(task);
    }

    public static final class Builder<E, K, D, F, V, S> {

        private final DataModelTypes<E, K, D, F, V, S> dataModelTypes;
        private final Map<Class<? extends TypeBasedTask<E, K, D, F, V, S, ?>>, Map<D, TaskHandler>> typeBasedTaskHandlers = new LinkedHashMap<>();
        private final Map<Class<? extends DatabaseTask<E, K, D, F, V, S, ?>>, TaskHandler> taskHandlers = new LinkedHashMap<>();

        private TaskHandler defaultTaskHandler;

        public Builder(DataModelTypes<E, K, D, F, V, S> dataModelTypes) {
            this.dataModelTypes = dataModelTypes;
        }

        public Builder<E, K, D, F, V, S> withDefaultTaskHandler(Function<Task<?>, ?> function) {
            this.defaultTaskHandler = function == null ? null : ScopedTaskHandler.create(function);
            return Types.cast(this);
        }

        public Builder<E, K, D, F, V, S> withDefaultTaskHandler(TaskHandler taskHandler) {
            this.defaultTaskHandler = taskHandler;
            return Types.cast(this);
        }

        public <T extends DatabaseTask<E, K, D, F, V, S, O>, O> Builder<E, K, D, F, V, S> withDatabaseTaskHandler(Class<T> taskType, Function<T, O> function) {
            return withDatabaseTaskHandler(taskType, function == null ? null : new FunctionTaskHandler<>(taskType, function));
        }

        public <T extends DatabaseTask<E, K, D, F, V, S, O>, O> Builder<E, K, D, F, V, S> withDatabaseTaskHandler(Class<T> taskType, TaskHandler taskHandler) {
            if (!DatabaseTask.class.isAssignableFrom(taskType)) {
                throw new IllegalArgumentException();
            }
            if (taskHandler != null) {
                taskHandlers.put(taskType, taskHandler);
            } else {
                taskHandlers.remove(taskType);
            }
            return Types.cast(this);
        }

        public <T extends TypeBasedTask<E, K, D, F, V, S, O>, O> Builder<E, K, D, F, V, S> withTypedDataTaskHandler(D dataType, Class<T> taskType, Function<T, O> function) {
            return withTypedDataTaskHandler(dataType, taskType, function == null ? null : new FunctionTaskHandler<>(taskType, function));
        }

        public <T extends TypeBasedTask<E, K, D, F, V, S, O>, O> Builder<E, K, D, F, V, S> withTypedDataTaskHandler(D dataType, Class<T> taskType, TaskHandler taskHandler) {
            Objects.requireNonNull(dataType);
            if (!TypeBasedTask.class.isAssignableFrom(taskType)) {
                throw new IllegalArgumentException();
            }
            if (taskHandler != null && !taskHandler.supportsTaskType(taskType)) {
                throw new IllegalArgumentException();
            }
            if (taskHandler != null) {
                typeBasedTaskHandlers.computeIfAbsent(taskType, d -> new LinkedHashMap<>()).put(dataType, taskHandler);
            } else {
                typeBasedTaskHandlers.computeIfAbsent(taskType, d -> new LinkedHashMap<>()).remove(dataType);
            }
            return Types.cast(this);
        }

        private void validate() {
            if (dataModelTypes == null) {
                throw new IllegalStateException();
            }
        }

        public DatabaseTaskHandler<E, K, D, F, V, S> build() {
            validate();
            return new DatabaseTaskHandler<>(dataModelTypes, typeBasedTaskHandlers, taskHandlers, defaultTaskHandler);
        }
    }
}
