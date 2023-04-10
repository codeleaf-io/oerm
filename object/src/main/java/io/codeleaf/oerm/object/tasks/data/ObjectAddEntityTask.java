package io.codeleaf.oerm.object.tasks.data;

import io.codeleaf.oerm.impl.tasks.data.GenericAddEntityTask;
import io.codeleaf.oerm.object.Entity;
import io.codeleaf.oerm.object.ObjectRepository;
import io.codeleaf.oerm.object.Reference;

import java.util.function.Supplier;

public final class ObjectAddEntityTask extends GenericAddEntityTask<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {

    public ObjectAddEntityTask(Class<? extends Entity> dataType, Entity entity) {
        super(ObjectRepository.GENERIC_TYPES, dataType, entity);
    }

    public static final class Builder extends GenericAddEntityTask.Builder<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {

        public Builder() {
            super(ObjectRepository.GENERIC_TYPES);
        }

        @Override
        public ObjectAddEntityTask build() {
            validate();
            return new ObjectAddEntityTask(dataType, entity);
        }
    }
}
