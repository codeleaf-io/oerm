package io.codeleaf.oerm.entity.tasks.meta;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.entity.EntityRecord;
import io.codeleaf.oerm.entity.EntityRepository;
import io.codeleaf.oerm.entity.EntitySchema;
import io.codeleaf.oerm.impl.tasks.meta.GenericAddDataTypeTask;

public final class EntityAddDataTypeTask extends GenericAddDataTypeTask<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

    public EntityAddDataTypeTask(String dataType, EntitySchema schema) {
        super(EntityRepository.GENERIC_TYPES, dataType, schema);
    }

    public static final class Builder extends GenericAddDataTypeTask.Builder<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

        public Builder() {
            super(EntityRepository.GENERIC_TYPES);
        }

        @Override
        public EntityAddDataTypeTask build() {
            validate();
            return new EntityAddDataTypeTask(dataType, schema);
        }
    }

}
