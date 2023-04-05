package io.codeleaf.oerm.generic;

import io.codeleaf.modeling.task.Task;
import io.codeleaf.modeling.task.TaskHandlingException;
import io.codeleaf.oerm.SearchCursorAndCount;
import io.codeleaf.oerm.SearchPageAndCount;
import io.codeleaf.oerm.generic.tasks.impl.*;
import io.codeleaf.oerm.impl.DefaultCount;

import java.io.IOException;
import java.util.Objects;

public class DelegatingDataTaskHandler<E, K, D, F, V, S> implements DataTaskHandler<E, K, D, F, V, S> {

    private final DataTaskHandler<E, K, D, F, V, S> dataTaskHandler;

    public DelegatingDataTaskHandler(DataTaskHandler<E, K, D, F, V, S> dataTaskHandler) {
        this.dataTaskHandler = dataTaskHandler;
    }

    @Override
    public <T extends Task<?>> boolean supportsTaskType(Class<T> taskType) {
        if (dataTaskHandler.supportsTaskType(taskType)) {
            return true;
        }
        if (Objects.equals(taskType, UpdateFieldsTaskImpl.class)) {
            if (dataTaskHandler.supportsTaskType(UpdateEntityTaskImpl.class) &&
                    dataTaskHandler.supportsTaskType(RetrieveTaskImpl.class)) {
                return true;
            }
        }
        if (Objects.equals(taskType, CreateEntityTaskImpl.class)) {
            if (dataTaskHandler.supportsTaskType(CreateEntityTaskImpl.class) &&
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
        if (Objects.equals(taskType, PageSearchAndCountTaskImpl.class)) {
            if ((dataTaskHandler.supportsTaskType(PageSearchTaskImpl.class) ||
                    dataTaskHandler.supportsTaskType(CursorSearchTaskImpl.class)) &&
                    dataTaskHandler.supportsTaskType(CountTaskImpl.class)) {
                return true;
            }
        }
        if (Objects.equals(taskType, CursorSearchTaskImpl.class)) {
            if (dataTaskHandler.supportsTaskType(PageSearchTaskImpl.class)) {
                return true;
            }
        }
        if (Objects.equals(taskType, PageSearchTaskImpl.class)) {
            if (dataTaskHandler.supportsTaskType(CursorSearchTaskImpl.class)) {
                return true;
            }
        }
        if (Objects.equals(taskType, CountTaskImpl.class)) {
            if (dataTaskHandler.supportsTaskType(CursorSearchAndCountTaskImpl.class) ||
                    dataTaskHandler.supportsTaskType(PageSearchAndCountTaskImpl.class)) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <O> O handleTask(Task<O> task) throws TaskHandlingException {
        if (dataTaskHandler.supportsTaskType(task.getClass())) {
            return dataTaskHandler.handleTask(task);
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
                if (dataTaskHandler.supportsTaskType(CursorSearchAndCountTaskImpl.class)) {
                    CursorSearchAndCountTaskImpl<D, F, V, E> searchTask = getTaskBuilders().cursorSearchAndCount()
                            .withDataType(countTask.getDataType())
                            .withSelection(countTask.getSelection())
                            .withBufferSize(1)
                            .build();
                    try (SearchCursorAndCount<E> search = dataTaskHandler.handleTask(searchTask)) {
                        return (O) new DefaultCount(search.getCount());
                    }
                } else if (dataTaskHandler.supportsTaskType(PageSearchAndCountTaskImpl.class)) {
                    PageSearchAndCountTaskImpl<D, F, V, E> searchTask = getTaskBuilders().pageSearchAndCount()
                            .withDataType(countTask.getDataType())
                            .withSelection(countTask.getSelection())
                            .withLimit(1)
                            .withOffset(0)
                            .build();
                    SearchPageAndCount<E> search = dataTaskHandler.handleTask(searchTask);
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
        return dataTaskHandler.getGenericTypes();
    }

}
