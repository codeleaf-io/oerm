package io.codeleaf.oerm.record;

import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.data.RecordType;
import io.codeleaf.oerm.Repository;
import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.impl.TypedRepositoryImpl;
import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.RecordWithType;
import io.codeleaf.modeling.data.ValueWithType;

public interface RecordRepository extends Repository<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

    DataModelTypes<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> GENERIC_TYPES = new DataModelTypes<>(
            RecordWithType.class,
            IdentifierWithType.class,
            String.class,
            String.class,
            Types.cast(ValueWithType.class),
            RecordType.class);

    @Override
    default DataModelTypes<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> getDataModelTypes() {
        return GENERIC_TYPES;
    }

    default RecordTypedRepository toTypedRepository(String dataType) {
        return Types.cast(
                TypedRepositoryImpl.create(this, dataType),
                RecordTypedRepository.class);
    }
}