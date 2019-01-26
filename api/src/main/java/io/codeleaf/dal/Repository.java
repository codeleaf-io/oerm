package io.codeleaf.dal;

import io.codeleaf.dal.tasks.*;
import io.codeleaf.dal.tasks.impl.*;
import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.modeling.task.TaskHandler;
import io.codeleaf.modeling.task.TaskHandlingException;

import java.util.Map;

public interface Repository<E, K, D, F, V, H> extends TaskHandler {

    RepositoryType<E, K, D, F, V, H> getRepositoryType();

    default E retrieve(D dataType, Selection selection) throws TaskHandlingException {
        RetrieveTaskImpl<D, F, V, E> retrieveTask =
                new RetrieveTaskImpl.Builder<>(dataType, getRepositoryType().getFieldNameType(),
                        getRepositoryType().getFieldValueType(), getRepositoryType().getObjectType())
                        .withSelection(selection)
                        .build();
        return read(retrieveTask);
    }

    default long count(D dataType, Selection selection) throws TaskHandlingException {
        CountTaskImpl<D, F, V> countTask =
                new CountTaskImpl.Builder<>(dataType, getRepositoryType().getFieldNameType(),
                        getRepositoryType().getFieldValueType())
                        .withSelection(selection)
                        .build();
        return read(countTask).getCount();
    }

    default SearchCursor<H> search(D dataType, Selection selection) throws TaskHandlingException {
        CursorSearchTaskImpl<D, F, V, H> cursorSearchTask =
                new CursorSearchTaskImpl.Builder<>(dataType, getRepositoryType().getFieldNameType(),
                        getRepositoryType().getFieldValueType(), getRepositoryType().getSearchHitType())
                        .withSelection(selection)
                        .build();
        return read(cursorSearchTask);
    }

    default SearchPage<H> search(D dataType, Selection selection, long offset, int limit) throws TaskHandlingException {
        PageSearchTaskImpl<D, F, V, H> pageSearchTask =
                new PageSearchTaskImpl.Builder<>(dataType, getRepositoryType().getFieldNameType(),
                        getRepositoryType().getFieldValueType(), getRepositoryType().getSearchHitType())
                        .withSelection(selection)
                        .withOffset(offset)
                        .withLimit(limit)
                        .build();
        return read(pageSearchTask);
    }

    default SearchCursorAndCount<H> searchAndCount(D dataType, Selection selection) throws TaskHandlingException {
        CursorSearchAndCountTaskImpl<D, F, V, H> cursorSearchAndCountTask =
                new CursorSearchAndCountTaskImpl.Builder<>(dataType, getRepositoryType().getFieldNameType(),
                        getRepositoryType().getFieldValueType(), getRepositoryType().getSearchHitType())
                        .withSelection(selection)
                        .build();
        return read(cursorSearchAndCountTask);
    }

    default SearchPageAndCount<H> searchAndCount(D dataType, Selection selection, long offset, int limit) throws TaskHandlingException {
        PageSearchAndCountTaskImpl<D, F, V, H> pageSearchAndCountTask =
                new PageSearchAndCountTaskImpl.Builder<>(dataType, getRepositoryType().getFieldNameType(),
                        getRepositoryType().getFieldValueType(), getRepositoryType().getSearchHitType())
                        .withSelection(selection)
                        .withOffset(offset)
                        .withLimit(limit)
                        .build();
        return read(pageSearchAndCountTask);
    }

    default <O> O read(ReadTask<D, O> readTask) throws TaskHandlingException {
        return handleTask(readTask);
    }

    default K create(D dataType, E object) throws TaskHandlingException {
        CreateObjectTaskImpl<E, K, D> createObjectTask =
                new CreateObjectTaskImpl.Builder<>(getRepositoryType().getObjectType(),
                        getRepositoryType().getObjectIdType(), dataType)
                        .withObject(object)
                        .build();
        return create(createObjectTask);
    }

    default K create(D dataType, Map<F, V> fields) throws TaskHandlingException {
        CreateFieldsTaskImpl<K, D, F, V> createFieldsTask =
                new CreateFieldsTaskImpl.Builder<>(getRepositoryType().getObjectIdType(), dataType,
                        getRepositoryType().getFieldNameType(), getRepositoryType().getFieldValueType())
                        .withFields(fields)
                        .build();
        return create(createFieldsTask);
    }

    default K create(CreateTask<K, D> createTask) throws TaskHandlingException {
        return handleTask(createTask);
    }

    default void update(D dataType, Selection selection, E object) throws TaskHandlingException {
        UpdateObjectTaskImpl<E, D, F, V> updateObjectTask =
                new UpdateObjectTaskImpl.Builder<>(getRepositoryType().getObjectType(), dataType,
                        getRepositoryType().getFieldNameType(), getRepositoryType().getFieldValueType())
                        .withSelection(selection)
                        .withObject(object)
                        .build();
        update(updateObjectTask);
    }

    default void update(D dataType, Selection selection, Map<F, V> fields) throws TaskHandlingException {
        UpdateFieldsTaskImpl<D, F, V> updateFieldsTask =
                new UpdateFieldsTaskImpl.Builder<>(dataType, getRepositoryType().getFieldNameType(),
                        getRepositoryType().getFieldValueType())
                        .withSelection(selection)
                        .withFields(fields)
                        .build();
        update(updateFieldsTask);
    }

    default <O> O update(UpdateTask<D, O> updateTask) throws TaskHandlingException {
        return handleTask(updateTask);
    }

    default void delete(D dataType, Selection selection) throws TaskHandlingException {
        DeleteTaskImpl<D, F, V> deleteTask =
                new DeleteTaskImpl.Builder<>(dataType, getRepositoryType().getFieldNameType(),
                        getRepositoryType().getFieldValueType())
                        .withSelection(selection)
                        .build();
        delete(deleteTask);
    }

    default <O> O delete(DeleteTask<D, F, V, O> deleteTask) throws TaskHandlingException {
        return handleTask(deleteTask);
    }
}
