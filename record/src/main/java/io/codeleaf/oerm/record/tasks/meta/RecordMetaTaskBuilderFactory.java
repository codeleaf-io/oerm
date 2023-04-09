package io.codeleaf.oerm.record.tasks.meta;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.RecordType;
import io.codeleaf.modeling.data.RecordWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.record.RecordRepository;
import io.codeleaf.oerm.tasks.meta.MetaTaskBuilderFactory;

public final class RecordMetaTaskBuilderFactory implements MetaTaskBuilderFactory<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {
    @Override
    public DataModelTypes<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> getDataModelTypes() {
        return RecordRepository.GENERIC_TYPES;
    }

    @Override
    public RecordSelectEntityTask.Builder select() {
        return new RecordSelectEntityTask.Builder();
    }

    @Override
    public RecordDetermineDataTypeTask.Builder determine() {
        return new RecordDetermineDataTypeTask.Builder();
    }

    @Override
    public RecordGetDataTypesTask.Builder list() {
        return new RecordGetDataTypesTask.Builder();
    }

    @Override
    public RecordAddDataTypeTask.Builder add() {
        return new RecordAddDataTypeTask.Builder();
    }

    @Override
    public RecordGetEntitySchemaTask.Builder get() {
        return new RecordGetEntitySchemaTask.Builder();
    }

    @Override
    public RecordRemoveDataTypeTask.Builder remove() {
        return new RecordRemoveDataTypeTask.Builder();
    }
}
