package io.codeleaf.oerm.object.tasks.data;

import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.impl.tasks.data.GenericUpdateFieldsTask;
import io.codeleaf.oerm.object.Entity;
import io.codeleaf.oerm.object.ObjectRepository;
import io.codeleaf.oerm.object.Reference;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class ObjectUpdateFieldsTask extends GenericUpdateFieldsTask<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {

    public ObjectUpdateFieldsTask(Class<? extends Entity> dataType, Selection selection, Map<Supplier<?>, Object> fields) {
        super(ObjectRepository.GENERIC_TYPES, dataType, selection, fields);
    }

    public static final class Builder extends GenericUpdateFieldsTask.Builder<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {

        public Builder() {
            super(ObjectRepository.GENERIC_TYPES);
        }

        @Override
        public ObjectUpdateFieldsTask build() {
            validate();
            return new ObjectUpdateFieldsTask(dataType, selection, Collections.unmodifiableMap(new LinkedHashMap<>(fields)));
        }
    }
}
