package io.codeleaf.dal.record;

import io.codeleaf.dal.generic.TypedRepository;
import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.RecordWithType;
import io.codeleaf.modeling.data.ValueWithType;

public interface RecordTypedRepository extends TypedRepository<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>> {
}
