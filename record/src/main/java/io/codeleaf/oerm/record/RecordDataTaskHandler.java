package io.codeleaf.oerm.record;

import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.RecordType;
import io.codeleaf.modeling.data.RecordWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.generic.DataTaskHandler;
import io.codeleaf.oerm.generic.RepositoryBridge;
import io.codeleaf.oerm.generic.RepositoryTypes;

public interface RecordDataTaskHandler extends DataTaskHandler<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

    @Override
    default RepositoryTypes<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> getGenericTypes() {
        return RecordRepository.GENERIC_TYPES;
    }

    default RecordRepository toRepository() {
        return Types.cast(
                RepositoryBridge.create(getGenericTypes(), this),
                RecordRepository.class);
    }
}
