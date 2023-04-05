package io.codeleaf.oerm.entity;

import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.generic.DataTaskHandler;
import io.codeleaf.oerm.generic.EntityHelper;
import io.codeleaf.oerm.generic.RepositoryBridge;
import io.codeleaf.oerm.generic.RepositoryTypes;

import java.util.Objects;

public interface EntityDataTaskHandler extends DataTaskHandler<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

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
