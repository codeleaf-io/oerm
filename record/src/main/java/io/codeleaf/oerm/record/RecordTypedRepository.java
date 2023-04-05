package io.codeleaf.oerm.record;

import io.codeleaf.modeling.data.RecordType;
import io.codeleaf.oerm.generic.TypedRepository;
import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.RecordWithType;
import io.codeleaf.modeling.data.ValueWithType;

public interface RecordTypedRepository extends TypedRepository<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {
}
