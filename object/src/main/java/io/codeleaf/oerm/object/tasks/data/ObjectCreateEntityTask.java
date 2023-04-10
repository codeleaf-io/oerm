package io.codeleaf.oerm.object.tasks.data;

import io.codeleaf.oerm.impl.tasks.data.GenericCreateEntityTask;
import io.codeleaf.oerm.object.Entity;
import io.codeleaf.oerm.object.ObjectRepository;
import io.codeleaf.oerm.object.Reference;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class ObjectCreateEntityTask extends GenericCreateEntityTask<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {

    public ObjectCreateEntityTask(Class<? extends Entity> dataType, Map<Supplier<?>, Object> fields) {
        super(ObjectRepository.GENERIC_TYPES, dataType, fields);
    }

    public static final class Builder extends GenericCreateEntityTask.Builder<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {

        public Builder() {
            super(ObjectRepository.GENERIC_TYPES);
        }

        @Override
        public ObjectCreateEntityTask build() {
            validate();
            return new ObjectCreateEntityTask(dataType, Collections.unmodifiableMap(new LinkedHashMap<>(fields)));
        }
    }
}
