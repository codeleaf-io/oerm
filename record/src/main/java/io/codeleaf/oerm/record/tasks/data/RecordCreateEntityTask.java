package io.codeleaf.oerm.record.tasks.data;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.RecordType;
import io.codeleaf.modeling.data.RecordWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.impl.tasks.data.GenericCreateEntityTask;
import io.codeleaf.oerm.record.RecordRepository;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class RecordCreateEntityTask extends GenericCreateEntityTask<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

    public RecordCreateEntityTask(String dataType, Map<String, ValueWithType<?>> fields) {
        super(RecordRepository.GENERIC_TYPES, dataType, fields);
    }

    public static final class Builder extends GenericCreateEntityTask.Builder<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

        public Builder() {
            super(RecordRepository.GENERIC_TYPES);
        }

        @Override
        public RecordCreateEntityTask build() {
            validate();
            return new RecordCreateEntityTask(dataType, Collections.unmodifiableMap(new LinkedHashMap<>(fields)));
        }
    }
}
