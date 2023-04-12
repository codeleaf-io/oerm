package io.codeleaf.oerm.entity.tasks.meta;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.entity.EntityRecord;
import io.codeleaf.oerm.entity.EntityRepository;
import io.codeleaf.oerm.entity.EntitySchema;
import io.codeleaf.oerm.impl.tasks.meta.GenericGetDataTypesTask;

public final class EntityGetDataTypesTask extends GenericGetDataTypesTask<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

    public EntityGetDataTypesTask() {
        super(EntityRepository.GENERIC_TYPES);
    }

    public static final class Builder extends GenericGetDataTypesTask.Builder<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

        public Builder() {
            super(EntityRepository.GENERIC_TYPES);
        }

        @Override
        public EntityGetDataTypesTask build() {
            validate();
            return new EntityGetDataTypesTask();
        }
    }
}
