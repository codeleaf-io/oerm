package io.codeleaf.oerm.entity;

import io.codeleaf.common.utils.Types;
import io.codeleaf.oerm.generic.DataTaskHandler;
import io.codeleaf.oerm.generic.EntityIdSelector;
import io.codeleaf.oerm.generic.RepositoryBridge;
import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;

import java.util.Objects;

public interface EntityDataTaskHandler extends DataTaskHandler<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>> {

    default EntityRepository toRepository(EntityIdSelector<String, IdentifierWithType> entityIdSelector) {
        Objects.requireNonNull(entityIdSelector);
        return Types.cast(
                RepositoryBridge.create(getGenericTypes(), this, entityIdSelector),
                EntityRepository.class);
    }
}
