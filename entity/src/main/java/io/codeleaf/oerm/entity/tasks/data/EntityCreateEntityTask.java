package io.codeleaf.oerm.entity.tasks.data;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.entity.EntityRecord;
import io.codeleaf.oerm.entity.EntityRepository;
import io.codeleaf.oerm.entity.EntitySchema;
import io.codeleaf.oerm.impl.tasks.data.GenericCreateEntityTask;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class EntityCreateEntityTask extends GenericCreateEntityTask<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

    public EntityCreateEntityTask(String dataType, Map<String, ValueWithType<?>> fields) {
        super(EntityRepository.GENERIC_TYPES, dataType, fields);
    }

    public static final class Builder extends GenericCreateEntityTask.Builder<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

        public Builder() {
            super(EntityRepository.GENERIC_TYPES);
        }

        @Override
        public EntityCreateEntityTask build() {
            validate();
            return new EntityCreateEntityTask(dataType, Collections.unmodifiableMap(new LinkedHashMap<>(fields)));
        }
    }
}
