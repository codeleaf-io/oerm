package io.codeleaf.oerm.record.tasks.data;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.RecordType;
import io.codeleaf.modeling.data.RecordWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.impl.tasks.data.GenericRemoveEntitiesTask;
import io.codeleaf.oerm.record.RecordRepository;

public final class RecordRemoveEntitiesTask extends GenericRemoveEntitiesTask<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

    public RecordRemoveEntitiesTask(String dataType, Selection selection) {
        super(RecordRepository.GENERIC_TYPES, dataType, selection);
    }

    public static final class Builder extends GenericRemoveEntitiesTask.Builder<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

        public Builder() {
            super(RecordRepository.GENERIC_TYPES);
        }

        @Override
        public RecordRemoveEntitiesTask build() {
            validate();
            return new RecordRemoveEntitiesTask(dataType, selection);
        }
    }
}
