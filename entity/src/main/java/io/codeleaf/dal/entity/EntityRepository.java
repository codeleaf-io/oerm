package io.codeleaf.dal.entity;

import io.codeleaf.common.utils.Types;
import io.codeleaf.dal.generic.Repository;
import io.codeleaf.dal.generic.RepositoryTypes;
import io.codeleaf.dal.generic.TypedRepositoryImpl;
import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;

public interface EntityRepository extends Repository<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>> {

    RepositoryTypes<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>> GENERIC_TYPES = new RepositoryTypes<>(
            EntityRecord.class,
            IdentifierWithType.class,
            String.class,
            String.class,
            Types.cast(ValueWithType.class));

    @Override
    default RepositoryTypes<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>> getGenericTypes() {
        return GENERIC_TYPES;
    }

    default EntityTypedRepository toTypedRepository(String dataType) {
        return Types.cast(
                TypedRepositoryImpl.create(this, dataType),
                EntityTypedRepository.class);
    }
}
