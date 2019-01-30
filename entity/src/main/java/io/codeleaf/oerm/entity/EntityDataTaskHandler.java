package io.codeleaf.oerm.entity;

import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.generic.DataTaskHandler;
import io.codeleaf.oerm.generic.EntitySelector;
import io.codeleaf.oerm.generic.RepositoryBridge;
import io.codeleaf.oerm.generic.RepositoryTypes;

import java.util.Objects;

public interface EntityDataTaskHandler extends DataTaskHandler<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>> {

    @Override
    default RepositoryTypes<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>> getGenericTypes() {
        return EntityRepository.GENERIC_TYPES;
    }

    default EntityRepository toRepository(EntitySelector<EntityRecord, IdentifierWithType, String> entitySelector) {
        Objects.requireNonNull(entitySelector);
        return Types.cast(
                RepositoryBridge.create(getGenericTypes(), this, entitySelector),
                EntityRepository.class);
    }
}
