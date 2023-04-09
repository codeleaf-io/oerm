package io.codeleaf.oerm.record.tasks.meta;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.RecordType;
import io.codeleaf.modeling.data.RecordWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.impl.tasks.meta.GenericDetermineDataTypeTask;
import io.codeleaf.oerm.record.RecordRepository;

public final class RecordDetermineDataTypeTask extends GenericDetermineDataTypeTask<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

    public RecordDetermineDataTypeTask(RecordWithType entity) {
        super(RecordRepository.GENERIC_TYPES, entity);
    }

    public static final class Builder extends GenericDetermineDataTypeTask.Builder<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

        public Builder() {
            super(RecordRepository.GENERIC_TYPES);
        }

        @Override
        public RecordDetermineDataTypeTask build() {
            validate();
            return new RecordDetermineDataTypeTask(entity);
        }
    }

}
