package io.codeleaf.dal;

import io.codeleaf.dal.generic.TypedRepository;
import io.codeleaf.dal.types.record.EntityRecord;
import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;

public interface RecordTypedRepository extends TypedRepository<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>> {
}
