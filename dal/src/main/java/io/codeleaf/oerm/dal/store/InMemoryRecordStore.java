package io.codeleaf.oerm.dal.store;

public final class InMemoryRecordStore { //implements RecordDataTaskHandler {
/*
    private final Map<Class<?>, DataTaskHandler<? extends DataTask<String, ?>, ?>> taskHandlers = new HashMap<>();
    private final Map<IdentifierWithType, RecordWithType> records = new HashMap<>();

    {
        initTaskHandlers();
    }

    @Override
    public <T extends Task<?>> boolean supportsTaskType(Class<T> taskTypeClass) {
        return taskTypeClass != null && taskHandlers.containsKey(taskTypeClass);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <O> O handleTask(Task<O> task) throws TaskHandlingException {
        Objects.requireNonNull(task);
        if (!supportsTaskType(task.getClass())) {
            throw new TaskHandlingException(task, "Unsupported task: " + task.getClass());
        }
        DataTaskHandler<DataTask<String, O>, O> dataTaskHandler = (DataTaskHandler<DataTask<String, O>, O>) taskHandlers.get(task.getClass());
        return dataTaskHandler.handleTask((DataTask<String, O>) task);
    }

    public boolean sameType(DataTask<String, ?> dataTask, IdentifierWithType recordId) {
        return dataTask.getDataType().equals(recordId.getType().getDataType());
    }

    public boolean matches(Selection selection, RecordWithType record) {
        return true;
    }

    @FunctionalInterface
    public interface DataTaskHandler<T extends DataTask<String, O>, O> {

        O handleTask(T task) throws TaskHandlingException;
    }

    private void initTaskHandlers() {
        taskHandlers.put(AddEntityTaskImpl.class,
                (DataTaskHandler<AddEntityTaskImpl<RecordWithType, IdentifierWithType, String>, IdentifierWithType>) task ->
                {
                    ValueWithType<?> valueWithType = task.getEntity().getValue().get("identifier");
                    if (!(valueWithType instanceof TextWithType)) {
                        throw new TaskHandlingException(task, "No identifier present!");
                    }
                    String identifier = ((TextWithType) valueWithType).getValue();
                    IdentifierWithType identifierWithType = IdentifierWithType.create(identifier, task.getDataType());
                    if (records.containsKey(identifierWithType)) {
                        throw new TaskHandlingException(task, "Already such an entity present: " + identifierWithType);
                    }
                    records.put(identifierWithType, task.getEntity());
                    return identifierWithType;
                });
        taskHandlers.put(RetrieveTaskImpl.class,
                (DataTaskHandler<RetrieveTask<String, String, ValueWithType<?>, RecordWithType>, RecordWithType>) task -> {
                    long count = 0;
                    RecordWithType result = null;
                    for (Map.Entry<IdentifierWithType, RecordWithType> entry : records.entrySet()) {
                        if (sameType(task, entry.getKey()) && matches(task.getSelection(), entry.getValue())) {
                            result = entry.getValue();
                            count++;
                        }
                    }
                    if (count == 0) {
                        throw new TaskHandlingException(task, "No entity found for selection!");
                    }
                    if (count > 1) {
                        throw new TaskHandlingException(task, "Multiple entities found for selection!");
                    }
                    return result;
                });
        taskHandlers.put(CountTaskImpl.class,
                (DataTaskHandler<CountTaskImpl<String, String, ValueWithType<?>>, Count>) task ->
                {
                    long count = 0;
                    for (Map.Entry<IdentifierWithType, RecordWithType> entry : records.entrySet()) {
                        if (sameType(task, entry.getKey()) && matches(task.getSelection(), entry.getValue())) {
                            count++;
                        }
                    }
                    return new DefaultCount(count);
                });
        taskHandlers.put(PageSearchTaskImpl.class,
                (DataTaskHandler<PageSearchTaskImpl<String, String, ValueWithType<?>, RecordWithType>, SearchPage<RecordWithType>>) task ->
                {
                    DefaultSearchPage<RecordWithType> page = new DefaultSearchPage<>();
                    long count = 0;
                    for (Map.Entry<IdentifierWithType, RecordWithType> entry : records.entrySet()) {
                        if (sameType(task, entry.getKey()) && matches(task.getSelection(), entry.getValue())) {
                            count++;
                            if (count > task.getOffset() && page.size() < task.getLimit()) {
                                page.add(entry.getValue());
                            }
                        }
                    }
                    return page;
                });
        taskHandlers.put(UpdateEntityTaskImpl.class,
                (DataTaskHandler<UpdateEntityTaskImpl<RecordWithType, String>, Void>) task ->
                {
                    ValueWithType<?> valueWithType = task.getEntity().getValue().get("identifier");
                    if (!(valueWithType instanceof TextWithType)) {
                        throw new TaskHandlingException(task, "No identifier present!");
                    }
                    String identifier = ((TextWithType) valueWithType).getValue();
                    IdentifierWithType identifierWithType = IdentifierWithType.create(identifier, task.getDataType());
                    if (!records.containsKey(identifierWithType)) {
                        throw new TaskHandlingException(task, "No such an entity present: " + identifierWithType);
                    }
                    records.put(identifierWithType, task.getEntity());
                    return null;
                });
        taskHandlers.put(RemoveTaskImpl.class,
                (DataTaskHandler<RemoveTaskImpl<String, String, ValueWithType<?>>, Count>) task ->
                {
                    Set<IdentifierWithType> toRemove = new HashSet<>();
                    for (Map.Entry<IdentifierWithType, RecordWithType> entry : records.entrySet()) {
                        if (sameType(task, entry.getKey()) && matches(task.getSelection(), entry.getValue())) {
                            toRemove.add(entry.getKey());
                        }
                    }
                    for (IdentifierWithType entityKey : toRemove) {
                        records.remove(entityKey);
                    }
                    return null;
                });
    }*/
}
