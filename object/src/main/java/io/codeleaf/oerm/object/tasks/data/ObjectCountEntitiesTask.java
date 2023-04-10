package io.codeleaf.oerm.object.tasks.data;

import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.impl.tasks.data.GenericCountEntitiesTask;
import io.codeleaf.oerm.object.Entity;
import io.codeleaf.oerm.object.ObjectRepository;
import io.codeleaf.oerm.object.Reference;

import java.util.function.Supplier;

public final class ObjectCountEntitiesTask extends GenericCountEntitiesTask<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {

    public ObjectCountEntitiesTask(Class<? extends Entity> dataType, Selection selection) {
        super(ObjectRepository.GENERIC_TYPES, dataType, selection);
    }

    public static final class Builder extends GenericCountEntitiesTask.Builder<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {

        public Builder() {
            super(ObjectRepository.GENERIC_TYPES);
        }

        @Override
        public ObjectCountEntitiesTask build() {
            validate();
            return new ObjectCountEntitiesTask(dataType, selection);
        }
    }
}
