package io.codeleaf.oerm.record.tasks.meta;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.RecordType;
import io.codeleaf.modeling.data.RecordWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.impl.tasks.meta.GenericSelectEntityTask;
import io.codeleaf.oerm.record.RecordRepository;

public final class RecordSelectEntityTask extends GenericSelectEntityTask<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

    public RecordSelectEntityTask(String dataType, IdentifierWithType entityId) {
        super(RecordRepository.GENERIC_TYPES, dataType, entityId);
    }

    public static final class Builder extends GenericSelectEntityTask.Builder<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

        public Builder() {
            super(RecordRepository.GENERIC_TYPES);
        }

        @Override
        public RecordSelectEntityTask build() {
            validate();
            return new RecordSelectEntityTask(dataType, entityId);
        }
    }

}
