package io.codeleaf.oerm.record.tasks.data;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.RecordType;
import io.codeleaf.modeling.data.RecordWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.impl.tasks.data.GenericAddEntityTask;
import io.codeleaf.oerm.record.RecordRepository;

public final class RecordAddEntityTask extends GenericAddEntityTask<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

    public RecordAddEntityTask(String dataType, RecordWithType entity) {
        super(RecordRepository.GENERIC_TYPES, dataType, entity);
    }

    public static final class Builder extends GenericAddEntityTask.Builder<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

        public Builder() {
            super(RecordRepository.GENERIC_TYPES);
        }

        @Override
        public RecordAddEntityTask build() {
            validate();
            return new RecordAddEntityTask(dataType, entity);
        }
    }
}
