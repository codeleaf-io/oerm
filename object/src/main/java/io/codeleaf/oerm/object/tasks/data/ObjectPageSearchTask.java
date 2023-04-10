package io.codeleaf.oerm.object.tasks.data;

import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.Ordering;
import io.codeleaf.oerm.impl.tasks.data.GenericPageSearchTask;
import io.codeleaf.oerm.object.Entity;
import io.codeleaf.oerm.object.ObjectRepository;
import io.codeleaf.oerm.object.Reference;

import java.util.List;
import java.util.function.Supplier;

public final class ObjectPageSearchTask extends GenericPageSearchTask<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {

    public ObjectPageSearchTask(Class<? extends Entity> dataType, Selection selection, List<Ordering<Supplier<?>>> order, List<Supplier<?>> projection, long offset, int limit) {
        super(ObjectRepository.GENERIC_TYPES, dataType, selection, order, projection, offset, limit);
    }

    public static final class Builder extends GenericPageSearchTask.Builder<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {

        public Builder() {
            super(ObjectRepository.GENERIC_TYPES);
        }

        @Override
        public ObjectPageSearchTask build() {
            validate();
            return new ObjectPageSearchTask(dataType, selection, order, projection, offset, limit);
        }
    }
}
