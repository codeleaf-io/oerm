package io.codeleaf.dal;

import io.codeleaf.common.utils.Types;
import io.codeleaf.dal.generic.DataTaskHandler;
import io.codeleaf.dal.generic.EntityIdSelector;
import io.codeleaf.dal.generic.RepositoryBridge;
import io.codeleaf.dal.types.record.EntityRecord;
import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;

import java.util.Objects;

public interface RecordDataTaskHandler extends DataTaskHandler<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>> {

    default RecordRepository toRepository(EntityIdSelector<String, IdentifierWithType> entityIdSelector) {
        Objects.requireNonNull(entityIdSelector);
        return Types.cast(
                RepositoryBridge.create(getGenericTypes(), this, entityIdSelector),
                RecordRepository.class);
    }
}
