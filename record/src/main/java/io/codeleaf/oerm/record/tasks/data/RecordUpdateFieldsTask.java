package io.codeleaf.oerm.record.tasks.data;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.RecordType;
import io.codeleaf.modeling.data.RecordWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.impl.tasks.data.GenericUpdateFieldsTask;
import io.codeleaf.oerm.record.RecordRepository;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class RecordUpdateFieldsTask extends GenericUpdateFieldsTask<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

    public RecordUpdateFieldsTask(String dataType, Selection selection, Map<String, ValueWithType<?>> fields) {
        super(RecordRepository.GENERIC_TYPES, dataType, selection, fields);
    }

    public static final class Builder extends GenericUpdateFieldsTask.Builder<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

        public Builder() {
            super(RecordRepository.GENERIC_TYPES);
        }

        @Override
        public RecordUpdateFieldsTask build() {
            validate();
            return new RecordUpdateFieldsTask(dataType, selection, Collections.unmodifiableMap(new LinkedHashMap<>(fields)));
        }
    }
}
