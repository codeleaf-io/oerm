package io.codeleaf.oerm.record.tasks.data;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.RecordType;
import io.codeleaf.modeling.data.RecordWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.impl.tasks.data.GenericRetrieveEntityTask;
import io.codeleaf.oerm.record.RecordRepository;

public final class RecordRetrieveEntityTask extends GenericRetrieveEntityTask<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

    public RecordRetrieveEntityTask(String dataType, Selection selection) {
        super(RecordRepository.GENERIC_TYPES, dataType, selection);
    }

    public static final class Builder extends GenericRetrieveEntityTask.Builder<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

        public Builder() {
            super(RecordRepository.GENERIC_TYPES);
        }

        @Override
        public RecordRetrieveEntityTask build() {
            validate();
            return new RecordRetrieveEntityTask(dataType, selection);
        }
    }
}
