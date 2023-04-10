package io.codeleaf.oerm.object.tasks.meta;

import io.codeleaf.oerm.impl.tasks.meta.GenericGetDataTypesTask;
import io.codeleaf.oerm.object.Entity;
import io.codeleaf.oerm.object.ObjectRepository;
import io.codeleaf.oerm.object.Reference;

import java.util.function.Supplier;

public final class ObjectGetDataTypesTask extends GenericGetDataTypesTask<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {

    public ObjectGetDataTypesTask() {
        super(ObjectRepository.GENERIC_TYPES);
    }

    public static final class Builder extends GenericGetDataTypesTask.Builder<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {

        public Builder() {
            super(ObjectRepository.GENERIC_TYPES);
        }

        @Override
        public ObjectGetDataTypesTask build() {
            validate();
            return new ObjectGetDataTypesTask();
        }
    }
}
