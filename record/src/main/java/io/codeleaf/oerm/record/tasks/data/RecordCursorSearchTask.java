package io.codeleaf.oerm.record.tasks.data;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.RecordType;
import io.codeleaf.modeling.data.RecordWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.Ordering;
import io.codeleaf.oerm.impl.tasks.data.GenericCursorSearchTask;
import io.codeleaf.oerm.record.RecordRepository;

import java.util.List;

public final class RecordCursorSearchTask extends GenericCursorSearchTask<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

    public RecordCursorSearchTask(String dataType, Selection selection, List<Ordering<String>> order, List<String> projection, int bufferSize) {
        super(RecordRepository.GENERIC_TYPES, dataType, selection, order, projection, bufferSize);
    }

    public static final class Builder extends GenericCursorSearchTask.Builder<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

        public Builder() {
            super(RecordRepository.GENERIC_TYPES);
        }

        @Override
        public GenericCursorSearchTask build() {
            validate();
            return new RecordCursorSearchTask(dataType, selection, order, projection, bufferSize);
        }
    }
}
