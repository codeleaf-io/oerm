package io.codeleaf.oerm.entity.tasks.data;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.entity.EntityRecord;
import io.codeleaf.oerm.entity.EntityRepository;
import io.codeleaf.oerm.entity.EntitySchema;
import io.codeleaf.oerm.impl.tasks.data.GenericAddEntityTask;

public final class EntityAddEntityTask extends GenericAddEntityTask<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

    public EntityAddEntityTask(String dataType, EntityRecord entity) {
        super(EntityRepository.GENERIC_TYPES, dataType, entity);
    }

    public static final class Builder extends GenericAddEntityTask.Builder<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

        public Builder() {
            super(EntityRepository.GENERIC_TYPES);
        }

        @Override
        public EntityAddEntityTask build() {
            validate();
            return new EntityAddEntityTask(dataType, entity);
        }
    }
}
