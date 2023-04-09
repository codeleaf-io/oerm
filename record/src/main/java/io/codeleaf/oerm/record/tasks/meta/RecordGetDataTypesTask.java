package io.codeleaf.oerm.record.tasks.meta;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.RecordType;
import io.codeleaf.modeling.data.RecordWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.impl.tasks.meta.GenericGetDataTypesTask;
import io.codeleaf.oerm.record.RecordRepository;

public final class RecordGetDataTypesTask extends GenericGetDataTypesTask<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

    public RecordGetDataTypesTask() {
        super(RecordRepository.GENERIC_TYPES);
    }

    public static final class Builder extends GenericGetDataTypesTask.Builder<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

        public Builder() {
            super(RecordRepository.GENERIC_TYPES);
        }

        @Override
        public RecordGetDataTypesTask build() {
            validate();
            return new RecordGetDataTypesTask();
        }
    }
}
