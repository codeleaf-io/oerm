package io.codeleaf.oerm.entity.tasks.meta;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.entity.EntityRecord;
import io.codeleaf.oerm.entity.EntityRepository;
import io.codeleaf.oerm.entity.EntitySchema;
import io.codeleaf.oerm.impl.tasks.meta.GenericRemoveDataTypeTask;

public final class EntityRemoveDataTypeTask extends GenericRemoveDataTypeTask<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

    public EntityRemoveDataTypeTask(String dataType) {
        super(EntityRepository.GENERIC_TYPES, dataType);
    }

    public static final class Builder extends GenericRemoveDataTypeTask.Builder<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

        public Builder() {
            super(EntityRepository.GENERIC_TYPES);
        }

        @Override
        public EntityRemoveDataTypeTask build() {
            validate();
            return new EntityRemoveDataTypeTask(dataType);
        }
    }

}
