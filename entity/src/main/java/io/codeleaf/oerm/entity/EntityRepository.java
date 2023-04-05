package io.codeleaf.oerm.entity;

import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.generic.Repository;
import io.codeleaf.oerm.generic.RepositoryTypes;
import io.codeleaf.oerm.generic.TypedRepositoryImpl;

public interface EntityRepository extends Repository<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

    RepositoryTypes<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> GENERIC_TYPES = new RepositoryTypes<>(
            EntityRecord.class,
            IdentifierWithType.class,
            String.class,
            String.class,
            Types.cast(ValueWithType.class),
            EntitySchema.class);

    @Override
    default RepositoryTypes<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> getGenericTypes() {
        return GENERIC_TYPES;
    }

    default EntityTypedRepository toTypedRepository(String dataType) {
        return Types.cast(
                TypedRepositoryImpl.create(this, dataType),
                EntityTypedRepository.class);
    }
}
