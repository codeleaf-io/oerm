package io.codeleaf.dal.entity;

import io.codeleaf.dal.generic.TypedRepository;
import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;

public interface EntityTypedRepository extends TypedRepository<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>> {
}
