package io.codeleaf.oerm.object.tasks.data;

import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.impl.tasks.data.GenericRetrieveEntityTask;
import io.codeleaf.oerm.object.Entity;
import io.codeleaf.oerm.object.ObjectRepository;
import io.codeleaf.oerm.object.Reference;

import java.util.function.Supplier;

public final class ObjectRetrieveEntityTask extends GenericRetrieveEntityTask<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {

    public ObjectRetrieveEntityTask(Class<? extends Entity> dataType, Selection selection) {
        super(ObjectRepository.GENERIC_TYPES, dataType, selection);
    }

    public static final class Builder extends GenericRetrieveEntityTask.Builder<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {

        public Builder() {
            super(ObjectRepository.GENERIC_TYPES);
        }

        @Override
        public ObjectRetrieveEntityTask build() {
            validate();
            return new ObjectRetrieveEntityTask(dataType, selection);
        }
    }
}
