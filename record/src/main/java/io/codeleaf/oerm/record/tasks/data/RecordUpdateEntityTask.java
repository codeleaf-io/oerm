package io.codeleaf.oerm.record.tasks.data;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.RecordType;
import io.codeleaf.modeling.data.RecordWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.impl.tasks.data.GenericUpdateEntityTask;
import io.codeleaf.oerm.record.RecordRepository;

public final class RecordUpdateEntityTask extends GenericUpdateEntityTask<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

    public RecordUpdateEntityTask(String dataType, RecordWithType entity) {
        super(RecordRepository.GENERIC_TYPES, dataType, entity);
    }

    public static final class Builder extends GenericUpdateEntityTask.Builder<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

        public Builder() {
            super(RecordRepository.GENERIC_TYPES);
        }

        @Override
        public RecordUpdateEntityTask build() {
            validate();
            return new RecordUpdateEntityTask(dataType, entity);
        }
    }
}
