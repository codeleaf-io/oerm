package io.codeleaf.oerm.handlers;

import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.task.Task;
import io.codeleaf.modeling.task.TaskCommand;
import io.codeleaf.modeling.task.TaskHandler;
import io.codeleaf.modeling.task.TaskHandlingException;
import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.tasks.DataTask;

import java.util.Objects;
import java.util.function.Function;

public final class DataTaskHandler<E, K, D, F, V, S, T extends DataTask<D, O>, O> implements TaskHandler {

    private final D dataType;
    private final Class<T> dataTaskType;
    private final DataModelTypes<E, K, D, F, V, S> dataModelTypes;
    private final TaskHandler taskHandler;
    private final Function<T, O> function;

    private DataTaskHandler(D dataType, Class<T> dataTaskType, DataModelTypes<E, K, D, F, V, S> dataModelTypes, TaskHandler taskHandler, Function<T, O> function) {
        this.dataType = dataType;
        this.dataTaskType = dataTaskType;
        this.dataModelTypes = dataModelTypes;
        this.taskHandler = taskHandler;
        this.function = function;
    }

    public D getDataType() {
        return dataType;
    }

    public DataModelTypes<E, K, D, F, V, S> getDataModelTypes() {
        return dataModelTypes;
    }

    public boolean supportsDataType(D dataType) {
        return this.dataType == null || Objects.equals(this.dataType, dataType);
    }

    @Override
    public <TL extends Task<?>> boolean supportsTaskType(Class<TL> taskType) {
        return Objects.equals(dataTaskType, taskType);
    }

    @Override
    public <OL> OL handleTask(Task<OL> task) throws TaskHandlingException {
        Objects.requireNonNull(task);
        if (!supportsTaskType(Types.cast(task.getClass()))) {
            throw new TaskHandlingException(task, "Unsupported task: " + task.getClass());
        }
        T dataTask = Types.cast(task);
        if (!supportsDataType(dataTask.getDataType())) {
            throw new TaskHandlingException(task, "Unsupported data type: " + dataTask.getDataType());
        }
        try {
            O output;
            if (taskHandler != null) {
                output = taskHandler.handleTask(dataTask);
            } else if (function != null) {
                output = function.apply(dataTask);
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
        private D dataType;
        private Class<? extends DataTask<?, ?>> dataTaskType;
        private TaskHandler taskHandler;
        private Function<?, ?> function;

        public Builder(DataModelTypes<E, K, D, F, V, S> dataModelTypes) {
            this.dataModelTypes = dataModelTypes;
        }

        public Builder<E, K, D, F, V, S> withDataType(D dataType) {
            Objects.requireNonNull(dataType);
            this.dataType = dataType;
            return this;
        }

        public <T extends DataTask<D, ?>> Builder<E, K, D, F, V, S> withDataTaskType(Class<T> dataTaskType) {
            Objects.requireNonNull(dataTaskType);
            this.dataTaskType = dataTaskType;
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
            if (dataType != null && !dataModelTypes.getDataTypeType().isInstance(dataType)) {
                throw new IllegalStateException();
            }
            if (dataTaskType == null || !DataTask.class.isAssignableFrom(dataTaskType)) {
                throw new IllegalStateException();
            }
            if (taskHandler == null && function == null) {
                throw new IllegalStateException();
            }
            if (taskHandler != null && (function != null || !taskHandler.supportsTaskType(dataTaskType))) {
                throw new IllegalStateException();
            }
        }

        public <T extends DataTask<D, O>, O> DataTaskHandler<E, K, D, F, V, S, ?, ?> build() {
            validate();
            Class<T> typedDataTaskType = Types.cast(dataTaskType);
            Function<T, O> typedFunction = Types.cast(function);
            return new DataTaskHandler<>(dataType, typedDataTaskType, dataModelTypes, taskHandler, typedFunction);
        }
    }
}
