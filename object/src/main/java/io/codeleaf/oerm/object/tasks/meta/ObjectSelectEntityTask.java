package io.codeleaf.oerm.object.tasks.meta;

import io.codeleaf.oerm.impl.tasks.meta.GenericSelectEntityTask;
import io.codeleaf.oerm.object.Entity;
import io.codeleaf.oerm.object.ObjectRepository;
import io.codeleaf.oerm.object.Reference;

import java.util.function.Supplier;

public final class ObjectSelectEntityTask extends GenericSelectEntityTask<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {

    public ObjectSelectEntityTask(Class<? extends Entity> dataType, Reference<? extends Entity> entityId) {
        super(ObjectRepository.GENERIC_TYPES, dataType, entityId);
    }

    public static final class Builder extends GenericSelectEntityTask.Builder<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {

        public Builder() {
            super(ObjectRepository.GENERIC_TYPES);
        }

        @Override
        public ObjectSelectEntityTask build() {
            validate();
            return new ObjectSelectEntityTask(dataType, entityId);
        }
    }

}
