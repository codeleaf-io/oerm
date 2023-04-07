package io.codeleaf.oerm.handlers;

import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.task.Task;
import io.codeleaf.modeling.task.TaskCommand;
import io.codeleaf.modeling.task.TaskHandler;
import io.codeleaf.modeling.task.TaskHandlingException;
import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.tasks.DataTypeTask;

import java.util.Objects;
import java.util.function.Function;

public final class DataTypeTaskHandler<E, K, D, F, V, S, T extends DataTypeTask<D, O>, O> implements TaskHandler {

    private final Class<T> dataTypeTaskType;
    private final DataModelTypes<E, K, D, F, V, S> dataModelTypes;
    private final TaskHandler taskHandler;
    private final Function<T, O> function;

    private DataTypeTaskHandler(Class<T> dataTypeTaskType, DataModelTypes<E, K, D, F, V, S> dataModelTypes, TaskHandler taskHandler, Function<T, O> function) {
        this.dataTypeTaskType = dataTypeTaskType;
        this.dataModelTypes = dataModelTypes;
        this.taskHandler = taskHandler;
        this.function = function;
    }

    public DataModelTypes<E, K, D, F, V, S> getDataModelTypes() {
        return dataModelTypes;
    }

    @Override
    public <TL extends Task<?>> boolean supportsTaskType(Class<TL> taskType) {
        return Objects.equals(dataTypeTaskType, taskType);
    }

    @Override
    public <OL> OL handleTask(Task<OL> task) throws TaskHandlingException {
        Objects.requireNonNull(task);
        if (!supportsTaskType(Types.cast(task.getClass()))) {
            throw new TaskHandlingException(task, "Unsupported task: " + task.getClass());
        }
        T dataTypeTask = Types.cast(task);
        try {
            O output;
            if (taskHandler != null) {
                output = taskHandler.handleTask(dataTypeTask);
            } else if (function != null) {
                output = function.apply(dataTypeTask);
            } else {
                throw new IllegalStateException("No taskHandler or function defined!");
            }
            return Types.cast(output);
        } catch (RuntimeException cause) {
            throw new TaskHandlingException(task, "Failed to handle task: " + cause, cause);
        }
    }

    @Override
    public <OL> TaskCommand<OL> createCommand(Task<OL> task) {
        Objects.requireNonNull(task);
        if (!supportsTaskType(Types.cast(task.getClass()))) {
            throw new IllegalArgumentException("Unsupported task: " + task.getClass());
        }
        return TaskCommand.create(this, task);
    }

    public static final class Builder<E, K, D, F, V, S> {

        private final DataModelTypes<E, K, D, F, V, S> dataModelTypes;
        private Class<? extends DataTypeTask<?, ?>> dataTypeTaskType;
        private TaskHandler taskHandler;
        private Function<?, ?> function;

        public Builder(DataModelTypes<E, K, D, F, V, S> dataModelTypes) {
            this.dataModelTypes = dataModelTypes;
        }

        public <T extends DataTypeTask<D, ?>> Builder<E, K, D, F, V, S> withDataTypeTaskType(Class<T> dataTaskType) {
            Objects.requireNonNull(dataTaskType);
            this.dataTypeTaskType = dataTaskType;
            return this;
        }

        public Builder<E, K, D, F, V, S> withTaskHandler(TaskHandler taskHandler) {
            Objects.requireNonNull(taskHandler);
            this.taskHandler = taskHandler;
            this.function = null;
            return this;
        }

        public Builder<E, K, D, F, V, S> withFunction(Function<?, ?> function) {
            Objects.requireNonNull(function);
            this.taskHandler = null;
            this.function = function;
            return this;
        }

        private void validate() {
            if (dataModelTypes == null) {
                throw new IllegalStateException();
            }
            if (dataTypeTaskType == null || !DataTypeTask.class.isAssignableFrom(dataTypeTaskType)) {
                throw new IllegalStateException();
            }
            if (taskHandler == null && function == null) {
                throw new IllegalStateException();
            }
            if (taskHandler != null && (function != null || !taskHandler.supportsTaskType(dataTypeTaskType))) {
                throw new IllegalStateException();
            }
        }

        public <T extends DataTypeTask<D, O>, O> DataTypeTaskHandler<E, K, D, F, V, S, ?, ?> build() {
            validate();
            Class<T> typedDataTaskType = Types.cast(dataTypeTaskType);
            Function<T, O> typedFunction = Types.cast(function);
            return new DataTypeTaskHandler<>(typedDataTaskType, dataModelTypes, taskHandler, typedFunction);
        }
    }
}
