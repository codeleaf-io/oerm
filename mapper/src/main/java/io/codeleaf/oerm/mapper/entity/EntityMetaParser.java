package io.codeleaf.oerm.mapper.entity;

import io.codeleaf.modeling.data.RecordWithType;
import io.codeleaf.oerm.entity.EntityRecord;

public interface EntityMetaParser {

    EntityRecord.Meta parseEntityMeta(RecordWithType entityRecordValue);
}
