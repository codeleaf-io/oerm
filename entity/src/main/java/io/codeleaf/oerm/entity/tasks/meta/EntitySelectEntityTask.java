package io.codeleaf.oerm.entity.tasks.meta;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.entity.EntityRecord;
import io.codeleaf.oerm.entity.EntityRepository;
import io.codeleaf.oerm.entity.EntitySchema;
import io.codeleaf.oerm.impl.tasks.meta.GenericSelectEntityTask;

public final class EntitySelectEntityTask extends GenericSelectEntityTask<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

    public EntitySelectEntityTask(String dataType, IdentifierWithType entityId) {
        super(EntityRepository.GENERIC_TYPES, dataType, entityId);
    }

    public static final class Builder extends GenericSelectEntityTask.Builder<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

        public Builder() {
            super(EntityRepository.GENERIC_TYPES);
        }

        @Override
        public EntitySelectEntityTask build() {
            validate();
            return new EntitySelectEntityTask(dataType, entityId);
        }
    }

}
