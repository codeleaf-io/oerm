package io.codeleaf.dal;

import io.codeleaf.dal.tasks.*;
import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.modeling.task.TaskHandlingException;

import java.util.Map;
import java.util.Objects;

public final class TypedRepository<E, K, D, F, V, H> {

    private final Repository<E, K, D, F, V, H> repository;
    private final D dataType;

    private TypedRepository(Repository<E, K, D, F, V, H> repository, D dataType) {
        this.repository = repository;
        this.dataType = dataType;
    }

    public Repository<E, K, D, F, V, H> getRepository() {
        return repository;
    }

    public D getDataType() {
        return dataType;
    }

    public E retrieve(Selection selection) throws TaskHandlingException {
        return repository.retrieve(dataType, selection);
    }

    public long count(Selection selection) throws TaskHandlingException {
        return repository.count(dataType, selection);
    }

    public SearchCursor<H> search(Selection selection) throws TaskHandlingException {
        return repository.search(dataType, selection);
    }

    public SearchPage<H> search(Selection selection, long offset, int limit) throws TaskHandlingException {
        return repository.search(dataType, selection, offset, limit);
    }

    public SearchCursorAndCount<H> searchAndCount(Selection selection) throws TaskHandlingException {
        return repository.searchAndCount(dataType, selection);
    }

    public SearchPageAndCount<H> searchAndCount(Selection selection, long offset, int limit) throws TaskHandlingException {
        return repository.searchAndCount(dataType, selection, offset, limit);
    }

    public <O> O read(ReadTask<D, O> readTask) throws TaskHandlingException {
        requireSameDataType(readTask);
        return repository.read(readTask);
    }

    public K create(E object) throws TaskHandlingException {
        return repository.create(dataType, object);
    }

    public K create(Map<F, V> fields) throws TaskHandlingException {
        return repository.create(dataType, fields);
    }

    public K create(CreateTask<K, D> createTask) throws TaskHandlingException {
        requireSameDataType(createTask);
        return repository.create(createTask);
    }

    public void update(Selection selection, E object) throws TaskHandlingException {
        repository.update(dataType, selection, object);
    }

    public void update(Selection selection, Map<F, V> fields) throws TaskHandlingException {
        repository.update(dataType, selection, fields);
    }

    public <O> O update(UpdateFieldsTask<D, F, V, O> updateFieldsTask) throws TaskHandlingException {
        requireSameDataType(updateFieldsTask);
        return repository.update(updateFieldsTask);
    }

    public void delete(Selection selection) throws TaskHandlingException {
        repository.delete(dataType, selection);
    }

    public <O> O delete(DeleteTask<D, F, V, O> deleteTask) throws TaskHandlingException {
        requireSameDataType(deleteTask);
        return repository.delete(deleteTask);
    }

    protected void requireSameDataType(DataTask<D, ?> dataTask) {
        if (dataTask == null || !Objects.equals(dataTask.getDataType(), dataType)) {
            throw new IllegalArgumentException("Invalid dataType defined in task "
                    + (dataTask == null ? "<null>" : dataTask.getDataType()) + ", we need " + dataType);
        }
    }

    public static <E, K, D, F, V, H> TypedRepository<E, K, D, F, V, H> create(Repository<E, K, D, F, V, H> repository, D dataType) {
        Objects.requireNonNull(repository);
        Objects.requireNonNull(dataType);
        return new TypedRepository<>(repository, dataType);
    }
}
