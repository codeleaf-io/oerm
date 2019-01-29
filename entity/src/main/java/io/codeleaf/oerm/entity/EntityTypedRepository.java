package io.codeleaf.oerm.entity;

import io.codeleaf.oerm.generic.TypedRepository;
import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;

public interface EntityTypedRepository extends TypedRepository<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>> {
}
