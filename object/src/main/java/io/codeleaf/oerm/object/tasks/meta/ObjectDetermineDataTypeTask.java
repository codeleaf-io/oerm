package io.codeleaf.oerm.object.tasks.meta;

import io.codeleaf.oerm.impl.tasks.meta.GenericDetermineDataTypeTask;
import io.codeleaf.oerm.object.Entity;
import io.codeleaf.oerm.object.ObjectRepository;
import io.codeleaf.oerm.object.Reference;

import java.util.function.Supplier;

public final class ObjectDetermineDataTypeTask extends GenericDetermineDataTypeTask<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {

    public ObjectDetermineDataTypeTask(Entity entity) {
        super(ObjectRepository.GENERIC_TYPES, entity);
    }

    public static final class Builder extends GenericDetermineDataTypeTask.Builder<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {

        public Builder() {
            super(ObjectRepository.GENERIC_TYPES);
        }

        @Override
        public ObjectDetermineDataTypeTask build() {
            validate();
            return new ObjectDetermineDataTypeTask(entity);
        }
    }

}
