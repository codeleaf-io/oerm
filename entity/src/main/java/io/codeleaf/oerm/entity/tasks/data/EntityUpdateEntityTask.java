package io.codeleaf.oerm.entity.tasks.data;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.entity.EntityRecord;
import io.codeleaf.oerm.entity.EntityRepository;
import io.codeleaf.oerm.entity.EntitySchema;
import io.codeleaf.oerm.impl.tasks.data.GenericUpdateEntityTask;

public final class EntityUpdateEntityTask extends GenericUpdateEntityTask<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

    public EntityUpdateEntityTask(String dataType, EntityRecord entity) {
        super(EntityRepository.GENERIC_TYPES, dataType, entity);
    }

    public static final class Builder extends GenericUpdateEntityTask.Builder<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

        public Builder() {
            super(EntityRepository.GENERIC_TYPES);
        }

        @Override
        public EntityUpdateEntityTask build() {
            validate();
            return new EntityUpdateEntityTask(dataType, entity);
        }
    }
}
