package io.codeleaf.oerm.object.tasks.data;

import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.Ordering;
import io.codeleaf.oerm.impl.tasks.data.GenericCursorSearchAndCountTask;
import io.codeleaf.oerm.object.Entity;
import io.codeleaf.oerm.object.ObjectRepository;
import io.codeleaf.oerm.object.Reference;

import java.util.List;
import java.util.function.Supplier;

public final class ObjectCursorSearchAndCountTask extends GenericCursorSearchAndCountTask<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {

    public ObjectCursorSearchAndCountTask(Class<? extends Entity> dataType, Selection selection, List<Ordering<Supplier<?>>> order, List<Supplier<?>> projection, int bufferSize) {
        super(ObjectRepository.GENERIC_TYPES, dataType, selection, order, projection, bufferSize);
    }

    public static final class Builder extends GenericCursorSearchAndCountTask.Builder<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {

        public Builder() {
            super(ObjectRepository.GENERIC_TYPES);
        }

        @Override
        public ObjectCursorSearchAndCountTask build() {
            validate();
            return new ObjectCursorSearchAndCountTask(dataType, selection, order, projection, bufferSize);
        }
    }
}
