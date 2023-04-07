package io.codeleaf.oerm.entity;

import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.Repository;
import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.impl.TypedRepositoryImpl;

public interface EntityRepository extends Repository<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

    DataModelTypes<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> GENERIC_TYPES = new DataModelTypes<>(
            EntityRecord.class,
            IdentifierWithType.class,
            String.class,
            String.class,
            Types.cast(ValueWithType.class),
            EntitySchema.class);

    @Override
    default DataModelTypes<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> getDataModelTypes() {
        return GENERIC_TYPES;
    }

    default EntityTypedRepository toTypedRepository(String dataType) {
        return Types.cast(
                TypedRepositoryImpl.create(this, dataType),
                EntityTypedRepository.class);
    }
}
