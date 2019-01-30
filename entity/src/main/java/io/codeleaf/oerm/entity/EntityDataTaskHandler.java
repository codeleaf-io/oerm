package io.codeleaf.oerm.entity;

import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.generic.DataTaskHandler;
import io.codeleaf.oerm.generic.EntitySelector;
import io.codeleaf.oerm.generic.RepositoryBridge;

import java.util.Objects;

public interface EntityDataTaskHandler extends DataTaskHandler<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>> {

    default EntityRepository toRepository(EntitySelector<EntityRecord, IdentifierWithType, String> entitySelector) {
        Objects.requireNonNull(entitySelector);
        return Types.cast(
                RepositoryBridge.create(getGenericTypes(), this, entitySelector),
                EntityRepository.class);
    }
}
