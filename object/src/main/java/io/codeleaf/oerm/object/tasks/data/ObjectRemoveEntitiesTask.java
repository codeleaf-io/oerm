package io.codeleaf.oerm.object.tasks.data;

import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.impl.tasks.data.GenericRemoveEntitiesTask;
import io.codeleaf.oerm.object.Entity;
import io.codeleaf.oerm.object.ObjectRepository;
import io.codeleaf.oerm.object.Reference;

import java.util.function.Supplier;

public final class ObjectRemoveEntitiesTask extends GenericRemoveEntitiesTask<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {

    public ObjectRemoveEntitiesTask(Class<? extends Entity> dataType, Selection selection) {
        super(ObjectRepository.GENERIC_TYPES, dataType, selection);
    }

    public static final class Builder extends GenericRemoveEntitiesTask.Builder<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {

        public Builder() {
            super(ObjectRepository.GENERIC_TYPES);
        }

        @Override
        public ObjectRemoveEntitiesTask build() {
            validate();
            return new ObjectRemoveEntitiesTask(dataType, selection);
        }
    }
}
