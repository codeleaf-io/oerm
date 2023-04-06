package io.codeleaf.oerm.generic.delegating;

import io.codeleaf.modeling.task.Task;
import io.codeleaf.modeling.task.TaskHandlingException;
import io.codeleaf.oerm.generic.DatabaseTaskHandler;
import io.codeleaf.oerm.generic.RepositoryTypes;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public final class DelegatingDataTaskHandler<E, K, D, F, V, S> implements DatabaseTaskHandler<E, K, D, F, V, S> {

    private final DatabaseTaskHandler<E, K, D, F, V, S> taskHandler;
    private final Set<DatabaseTaskHandler<E, K, D, F, V, S>> delegators;

    public static <E, K, D, F, V, S> DelegatingDataTaskHandler<E, K, D, F, V, S> create(DatabaseTaskHandler<E, K, D, F, V, S> taskHandler) {
        Set<DatabaseTaskHandler<E, K, D, F, V, S>> delegators = new LinkedHashSet<>();
        DelegatingDataTaskHandler<E, K, D, F, V, S> handler = new DelegatingDataTaskHandler<>(taskHandler, delegators);
        delegators.add(new DelegatingCountTaskHandler<>(() -> handler));
        delegators.add(new DelegatingPageSearchAndCountHandler<>(() -> handler));
        delegators.add(new DelegatingCursorSearchAndCountTaskHandler<>(() -> handler));
        return handler;
    }

    public DelegatingDataTaskHandler(DatabaseTaskHandler<E, K, D, F, V, S> taskHandler, Set<DatabaseTaskHandler<E, K, D, F, V, S>> delegators) {
        this.taskHandler = taskHandler;
        this.delegators = delegators;
    }

    @Override
    public <T extends Task<?>> boolean supportsTaskType(Class<T> taskType) {
        if (taskHandler.supportsTaskType(taskType)) {
            return true;
        }
        return delegators.stream().anyMatch(h -> h.supportsTaskType(taskType));
    }

    @Override
    public <O> O handleTask(Task<O> task) throws TaskHandlingException {
        if (taskHandler.supportsTaskType(task.getClass())) {
            return taskHandler.handleTask(task);
        }
        Optional<DatabaseTaskHandler<E, K, D, F, V, S>> handler = delegators.stream().filter(h -> h.supportsTaskType(task.getClass())).findFirst();
        if (handler.isEmpty()) {
            throw new TaskHandlingException(task, "Unsupported task!");
        }
        return handler.get().handleTask(task);
    }

    @Override
    public RepositoryTypes<E, K, D, F, V, S> getGenericTypes() {
        return taskHandler.getGenericTypes();
    }

}
