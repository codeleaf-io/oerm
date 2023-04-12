package io.codeleaf.oerm.entity.tasks.meta;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.entity.EntityRecord;
import io.codeleaf.oerm.entity.EntityRepository;
import io.codeleaf.oerm.entity.EntitySchema;
import io.codeleaf.oerm.impl.tasks.meta.GenericGetEntitySchemaTask;

public final class EntityGetEntitySchemaTask extends GenericGetEntitySchemaTask<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

    public EntityGetEntitySchemaTask(String dataType) {
        super(EntityRepository.GENERIC_TYPES, dataType);
    }

    public static final class Builder extends GenericGetEntitySchemaTask.Builder<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

        public Builder() {
            super(EntityRepository.GENERIC_TYPES);
        }

        @Override
        public EntityGetEntitySchemaTask build() {
            validate();
            return new EntityGetEntitySchemaTask(dataType);
        }
    }

}
