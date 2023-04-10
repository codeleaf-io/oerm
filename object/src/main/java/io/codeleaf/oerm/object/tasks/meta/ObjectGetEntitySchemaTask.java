package io.codeleaf.oerm.object.tasks.meta;

import io.codeleaf.oerm.impl.tasks.meta.GenericGetEntitySchemaTask;
import io.codeleaf.oerm.object.Entity;
import io.codeleaf.oerm.object.ObjectRepository;
import io.codeleaf.oerm.object.Reference;

import java.util.function.Supplier;

public final class ObjectGetEntitySchemaTask extends GenericGetEntitySchemaTask<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {

    public ObjectGetEntitySchemaTask(Class<? extends Entity> dataType) {
        super(ObjectRepository.GENERIC_TYPES, dataType);
    }

    public static final class Builder extends GenericGetEntitySchemaTask.Builder<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {

        public Builder() {
            super(ObjectRepository.GENERIC_TYPES);
        }

        @Override
        public ObjectGetEntitySchemaTask build() {
            validate();
            return new ObjectGetEntitySchemaTask(dataType);
        }
    }

}
