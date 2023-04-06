package io.codeleaf.oerm.generic;

import io.codeleaf.modeling.task.Task;
import io.codeleaf.modeling.task.TaskHandlingException;
import io.codeleaf.oerm.SearchCursorAndCount;
import io.codeleaf.oerm.SearchPageAndCount;
import io.codeleaf.oerm.generic.tasks.impl.*;
import io.codeleaf.oerm.impl.DefaultCount;

import java.io.IOException;
import java.util.Objects;

public class DelegatingDataTaskHandler<E, K, D, F, V, S> implements DatabaseTaskHandler<E, K, D, F, V, S> {

    private final DatabaseTaskHandler<E, K, D, F, V, S> taskHandler;

    public DelegatingDataTaskHandler(DatabaseTaskHandler<E, K, D, F, V, S> taskHandler) {
        this.taskHandler = taskHandler;
    }

    @Override
    public <T extends Task<?>> boolean supportsTaskType(Class<T> taskType) {
        if (taskHandler.supportsTaskType(taskType)) {
            return true;
        }
        if (Objects.equals(taskType, UpdateFieldsTaskImpl.class)) {
            if (taskHandler.supportsTaskType(UpdateEntityTaskImpl.class) &&
                    taskHandler.supportsTaskType(RetrieveTaskImpl.class)) {
                return true;
            }
        }
        if (Objects.equals(taskType, CreateEntityTaskImpl.class)) {
            if (taskHandler.supportsTaskType(CreateEntityTaskImpl.class) &&
                    taskHandler.supportsTaskType(RetrieveTaskImpl.class)) {
                return true;
            }
        }
        if (Objects.equals(taskType, CursorSearchAndCountTaskImpl.class)) {
            if ((taskHandler.supportsTaskType(CursorSearchTaskImpl.class) ||
                    taskHandler.supportsTaskType(PageSearchTaskImpl.class)) &&
                    taskHandler.supportsTaskType(CountTaskImpl.class)) {
                return true;
            }
        }
        if (Objects.equals(taskType, PageSearchAndCountTaskImpl.class)) {
            if ((taskHandler.supportsTaskType(PageSearchTaskImpl.class) ||
                    taskHandler.supportsTaskType(CursorSearchTaskImpl.class)) &&
                    taskHandler.supportsTaskType(CountTaskImpl.class)) {
                return true;
            }
        }
        if (Objects.equals(taskType, CursorSearchTaskImpl.class)) {
            if (taskHandler.supportsTaskType(PageSearchTaskImpl.class)) {
                return true;
            }
        }
        if (Objects.equals(taskType, PageSearchTaskImpl.class)) {
            if (taskHandler.supportsTaskType(CursorSearchTaskImpl.class)) {
                return true;
            }
        }
        if (Objects.equals(taskType, CountTaskImpl.class)) {
            if (taskHandler.supportsTaskType(CursorSearchAndCountTaskImpl.class) ||
                    taskHandler.supportsTaskType(PageSearchAndCountTaskImpl.class)) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <O> O handleTask(Task<O> task) throws TaskHandlingException {
        if (taskHandler.supportsTaskType(task.getClass())) {
            return taskHandler.handleTask(task);
        }
        try {
            Class<?> taskType = task.getClass();
        /*
            if (Objects.equals(taskType, UpdateFieldsTaskImpl.class)) {
                if (dataTaskHandler.supportsTaskType(UpdateEntityTaskImpl.class) &&
                        dataTaskHandler.supportsTaskType(RetrieveTaskImpl.class)) {
                    return true;
                }
            }
            if (Objects.equals(taskType, CursorSearchAndCountTaskImpl.class)) {
                if ((dataTaskHandler.supportsTaskType(CursorSearchTaskImpl.class) ||
                        dataTaskHandler.supportsTaskType(PageSearchTaskImpl.class)) &&
                        dataTaskHandler.supportsTaskType(CountTaskImpl.class)) {
                    return true;
                }
            }
         */
            if (Objects.equals(taskType, CountTaskImpl.class)) {
                CountTaskImpl<D, F, V> countTask = (CountTaskImpl<D, F, V>) task;
                if (taskHandler.supportsTaskType(CursorSearchAndCountTaskImpl.class)) {
                    CursorSearchAndCountTaskImpl<D, F, V, E> searchTask = getDataTaskBuilders(countTask.getDataType())
                            .cursorSearchAndCount()
                            .withSelection(countTask.getSelection())
                            .withBufferSize(1)
                            .build();
                    try (SearchCursorAndCount<E> search = taskHandler.handleTask(searchTask)) {
                        return (O) new DefaultCount(search.getCount());
                    }
                } else if (taskHandler.supportsTaskType(PageSearchAndCountTaskImpl.class)) {
                    PageSearchAndCountTaskImpl<D, F, V, E> searchTask = getDataTaskBuilders(countTask.getDataType())
                            .pageSearchAndCount()
                            .withSelection(countTask.getSelection())
                            .withLimit(1)
                            .withOffset(0)
                            .build();
                    SearchPageAndCount<E> search = taskHandler.handleTask(searchTask);
                    return (O) new DefaultCount(search.getCount());
                }
            }
            throw new IllegalArgumentException("Unsupported task!");
        }  catch (RuntimeException | IOException cause) {
            throw new TaskHandlingException(task, "Failed to handle task: " + task + ": " + cause.getMessage(), cause);
        }
    }

    @Override
    public RepositoryTypes<E, K, D, F, V, S> getGenericTypes() {
        return taskHandler.getGenericTypes();
    }

}
