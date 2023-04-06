package io.codeleaf.oerm.entity;

import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.generic.DatabaseTaskHandler;
import io.codeleaf.oerm.generic.RepositoryBridge;
import io.codeleaf.oerm.generic.RepositoryTypes;

public interface EntityDataTaskHandler extends DatabaseTaskHandler<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

    @Override
    default RepositoryTypes<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> getGenericTypes() {
        return EntityRepository.GENERIC_TYPES;
    }

    default EntityRepository toRepository() {
        return Types.cast(
                RepositoryBridge.create(getGenericTypes(), this),
                EntityRepository.class);
    }
}
