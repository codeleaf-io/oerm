package io.codeleaf.oerm.record.tasks.data;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.RecordType;
import io.codeleaf.modeling.data.RecordWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.Ordering;
import io.codeleaf.oerm.impl.tasks.data.GenericCursorSearchAndCountTask;
import io.codeleaf.oerm.record.RecordRepository;

import java.util.List;

public final class RecordCursorSearchAndCountTask extends GenericCursorSearchAndCountTask<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

    public RecordCursorSearchAndCountTask(String dataType, Selection selection, List<Ordering<String>> order, List<String> projection, int bufferSize) {
        super(RecordRepository.GENERIC_TYPES, dataType, selection, order, projection, bufferSize);
    }

    public static final class Builder extends GenericCursorSearchAndCountTask.Builder<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

        public Builder() {
            super(RecordRepository.GENERIC_TYPES);
        }

        @Override
        public RecordCursorSearchAndCountTask build() {
            validate();
            return new RecordCursorSearchAndCountTask(dataType, selection, order, projection, bufferSize);
        }
    }
}
