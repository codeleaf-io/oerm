package io.codeleaf.oerm.record;

import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.RecordWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.generic.DataTaskHandler;
import io.codeleaf.oerm.generic.EntitySelector;
import io.codeleaf.oerm.generic.RepositoryBridge;
import io.codeleaf.oerm.generic.RepositoryTypes;

import java.util.Objects;

public interface RecordDataTaskHandler extends DataTaskHandler<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>> {

    @Override
    default RepositoryTypes<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>> getGenericTypes() {
        return RecordRepository.GENERIC_TYPES;
    }

    default RecordRepository toRepository(EntitySelector<RecordWithType, IdentifierWithType, String> recordIdSelector) {
        Objects.requireNonNull(recordIdSelector);
        return Types.cast(
                RepositoryBridge.create(getGenericTypes(), this, recordIdSelector),
                RecordRepository.class);
    }
}
