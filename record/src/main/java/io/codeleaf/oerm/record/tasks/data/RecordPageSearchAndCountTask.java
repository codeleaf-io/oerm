package io.codeleaf.oerm.record.tasks.data;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.RecordType;
import io.codeleaf.modeling.data.RecordWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.Ordering;
import io.codeleaf.oerm.impl.tasks.data.GenericPageSearchAndCountTask;
import io.codeleaf.oerm.record.RecordRepository;

import java.util.List;

public final class RecordPageSearchAndCountTask extends GenericPageSearchAndCountTask<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

    public RecordPageSearchAndCountTask(String dataType, Selection selection, List<Ordering<String>> order, List<String> projection, long offset, int limit) {
        super(RecordRepository.GENERIC_TYPES, dataType, selection, order, projection, offset, limit);
    }

    public static final class Builder extends GenericPageSearchAndCountTask.Builder<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

        public Builder() {
            super(RecordRepository.GENERIC_TYPES);
        }

        @Override
        public RecordPageSearchAndCountTask build() {
            validate();
            return new RecordPageSearchAndCountTask(dataType, selection, order, projection, offset, limit);
        }
    }
}
