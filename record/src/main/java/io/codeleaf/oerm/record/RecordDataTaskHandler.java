package io.codeleaf.oerm.record;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.RecordType;
import io.codeleaf.modeling.data.RecordWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.modeling.task.TaskHandler;
import io.codeleaf.oerm.handlers.CompositeDatabaseTaskHandler;
import io.codeleaf.oerm.tasks.DataTask;
import io.codeleaf.oerm.tasks.DatabaseTask;

import java.util.Map;

public final class RecordDataTaskHandler extends CompositeDatabaseTaskHandler<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType, RecordRepository> {

    public RecordDataTaskHandler(Map<Class<? extends DataTask<?, ?>>, Map<String, TaskHandler>> dataTaskHandlers, Map<Class<? extends DatabaseTask<?>>, TaskHandler> taskHandlers, TaskHandler defaultTaskHandler) {
        super(dataTaskHandlers, taskHandlers, defaultTaskHandler);
    }

    public static final class Builder extends CompositeDatabaseTaskHandler.Builder<
            Builder,
            RecordDataTaskHandler,
            RecordRepository,
            RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

        public Builder() {
            super(RecordRepository.GENERIC_TYPES);
        }

        @Override
        public RecordDataTaskHandler build() {
            return new RecordDataTaskHandler(dataTaskHandlers, taskHandlers, defaultTaskHandler);
        }
    }
}
