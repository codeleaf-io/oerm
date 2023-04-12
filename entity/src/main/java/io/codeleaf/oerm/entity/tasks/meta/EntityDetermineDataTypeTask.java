package io.codeleaf.oerm.entity.tasks.meta;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.entity.EntityRecord;
import io.codeleaf.oerm.entity.EntityRepository;
import io.codeleaf.oerm.entity.EntitySchema;
import io.codeleaf.oerm.impl.tasks.meta.GenericDetermineDataTypeTask;

public final class EntityDetermineDataTypeTask extends GenericDetermineDataTypeTask<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

    public EntityDetermineDataTypeTask(EntityRecord entity) {
        super(EntityRepository.GENERIC_TYPES, entity);
    }

    public static final class Builder extends GenericDetermineDataTypeTask.Builder<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

        public Builder() {
            super(EntityRepository.GENERIC_TYPES);
        }

        @Override
        public EntityDetermineDataTypeTask build() {
            validate();
            return new EntityDetermineDataTypeTask(entity);
        }
    }

}
