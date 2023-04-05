package io.codeleaf.oerm.entity;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.generic.TypedRepository;

public interface EntityTypedRepository extends TypedRepository<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {
}
