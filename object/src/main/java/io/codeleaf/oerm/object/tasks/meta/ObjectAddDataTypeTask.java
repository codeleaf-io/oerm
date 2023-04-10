package io.codeleaf.oerm.object.tasks.meta;

import io.codeleaf.oerm.impl.tasks.meta.GenericAddDataTypeTask;
import io.codeleaf.oerm.object.Entity;
import io.codeleaf.oerm.object.ObjectRepository;
import io.codeleaf.oerm.object.Reference;

import java.util.function.Supplier;

public final class ObjectAddDataTypeTask extends GenericAddDataTypeTask<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {

    public ObjectAddDataTypeTask(Class<? extends Entity> dataType, Class<? extends Entity> schema) {
        super(ObjectRepository.GENERIC_TYPES, dataType, schema);
    }

    public static final class Builder extends GenericAddDataTypeTask.Builder<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {

        public Builder() {
            super(ObjectRepository.GENERIC_TYPES);
        }

        @Override
        public ObjectAddDataTypeTask build() {
            validate();
            return new ObjectAddDataTypeTask(dataType, schema);
        }
    }

}
