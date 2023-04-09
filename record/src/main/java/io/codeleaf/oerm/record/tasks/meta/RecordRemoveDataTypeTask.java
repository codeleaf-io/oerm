package io.codeleaf.oerm.record.tasks.meta;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.RecordType;
import io.codeleaf.modeling.data.RecordWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.impl.tasks.meta.GenericRemoveDataTypeTask;
import io.codeleaf.oerm.record.RecordRepository;

public final class RecordRemoveDataTypeTask extends GenericRemoveDataTypeTask<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

    public RecordRemoveDataTypeTask(String dataType) {
        super(RecordRepository.GENERIC_TYPES, dataType);
    }

    public static final class Builder extends GenericRemoveDataTypeTask.Builder<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

        public Builder() {
            super(RecordRepository.GENERIC_TYPES);
        }

        @Override
        public RecordRemoveDataTypeTask build() {
            validate();
            return new RecordRemoveDataTypeTask(dataType);
        }
    }

}
