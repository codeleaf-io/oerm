package io.codeleaf.oerm.record.tasks.meta;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.RecordType;
import io.codeleaf.modeling.data.RecordWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.impl.tasks.meta.GenericGetEntitySchemaTask;
import io.codeleaf.oerm.record.RecordRepository;

public final class RecordGetEntitySchemaTask extends GenericGetEntitySchemaTask<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

    public RecordGetEntitySchemaTask(String dataType) {
        super(RecordRepository.GENERIC_TYPES, dataType);
    }

    public static final class Builder extends GenericGetEntitySchemaTask.Builder<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

        public Builder() {
            super(RecordRepository.GENERIC_TYPES);
        }

        @Override
        public RecordGetEntitySchemaTask build() {
            validate();
            return new RecordGetEntitySchemaTask(dataType);
        }
    }

}
