package io.codeleaf.oerm.record.tasks.data;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.RecordType;
import io.codeleaf.modeling.data.RecordWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.record.RecordRepository;
import io.codeleaf.oerm.tasks.data.DataTaskBuilderFactory;

public final class RecordDataTaskBuilderFactory implements DataTaskBuilderFactory<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

    @Override
    public DataModelTypes<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> getDataModelTypes() {
        return RecordRepository.GENERIC_TYPES;
    }

    @Override
    public RecordCountEntitiesTask.Builder count() {
        return new RecordCountEntitiesTask.Builder();
    }

    @Override
    public RecordCreateEntityTask.Builder create() {
        return new RecordCreateEntityTask.Builder();
    }

    @Override
    public RecordAddEntityTask.Builder add() {
        return new RecordAddEntityTask.Builder();
    }

    @Override
    public RecordCursorSearchAndCountTask.Builder cursorSearchAndCount() {
        return new RecordCursorSearchAndCountTask.Builder();
    }

    @Override
    public RecordCursorSearchTask.Builder cursorSearch() {
        return new RecordCursorSearchTask.Builder();
    }

    @Override
    public RecordPageSearchAndCountTask.Builder pageSearchAndCount() {
        return new RecordPageSearchAndCountTask.Builder();
    }

    @Override
    public RecordPageSearchTask.Builder pageSearch() {
        return new RecordPageSearchTask.Builder();
    }

    @Override
    public RecordRemoveEntitiesTask.Builder remove() {
        return new RecordRemoveEntitiesTask.Builder();
    }

    @Override
    public RecordRetrieveEntityTask.Builder retrieve() {
        return new RecordRetrieveEntityTask.Builder();
    }

    @Override
    public RecordUpdateFieldsTask.Builder updateFields() {
        return new RecordUpdateFieldsTask.Builder();
    }

    @Override
    public RecordUpdateEntityTask.Builder updateEntity() {
        return new RecordUpdateEntityTask.Builder();
    }
}
