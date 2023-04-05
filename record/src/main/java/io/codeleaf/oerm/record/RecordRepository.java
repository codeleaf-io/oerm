package io.codeleaf.oerm.record;

import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.data.RecordType;
import io.codeleaf.oerm.generic.Repository;
import io.codeleaf.oerm.generic.RepositoryTypes;
import io.codeleaf.oerm.generic.TypedRepositoryImpl;
import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.RecordWithType;
import io.codeleaf.modeling.data.ValueWithType;

public interface RecordRepository extends Repository<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

    RepositoryTypes<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> GENERIC_TYPES = new RepositoryTypes<>(
            RecordWithType.class,
            IdentifierWithType.class,
            String.class,
            String.class,
            Types.cast(ValueWithType.class),
            RecordType.class);

    @Override
    default RepositoryTypes<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> getGenericTypes() {
        return GENERIC_TYPES;
    }

    default RecordTypedRepository toTypedRepository(String dataType) {
        return Types.cast(
                TypedRepositoryImpl.create(this, dataType),
                RecordTypedRepository.class);
    }
}