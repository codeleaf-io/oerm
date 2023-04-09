package io.codeleaf.oerm.record.tasks.meta;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.RecordType;
import io.codeleaf.modeling.data.RecordWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.impl.tasks.meta.GenericAddDataTypeTask;
import io.codeleaf.oerm.record.RecordRepository;

public final class RecordAddDataTypeTask extends GenericAddDataTypeTask<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

    public RecordAddDataTypeTask(String dataType, RecordType schema) {
        super(RecordRepository.GENERIC_TYPES, dataType, schema);
    }

    public static final class Builder extends GenericAddDataTypeTask.Builder<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

        public Builder() {
            super(RecordRepository.GENERIC_TYPES);
        }

        @Override
        public RecordAddDataTypeTask build() {
            validate();
            return new RecordAddDataTypeTask(dataType, schema);
        }
    }

}
