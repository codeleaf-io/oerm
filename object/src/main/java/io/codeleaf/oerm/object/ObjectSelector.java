package io.codeleaf.oerm.object;

import io.codeleaf.common.utils.MethodReferences;
import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.modeling.selection.SelectionBuilder;
import io.codeleaf.oerm.generic.EntitySelector;

import java.util.function.Supplier;

public final class ObjectSelector<E extends Entity> implements EntitySelector<E, Reference<E>, Class<? extends E>> {

    @Override
    public Selection select(Class<? extends E> dataType, Reference<E> objectId) {
        return SelectionBuilder.create(Types.<Class<Supplier<?>>>cast(Supplier.class), Object.class)
                .field(MethodReferences.getProxy(Entity.class)::getIdentifier).equalTo(objectId);
    }

    @Override
    public Reference<E> getEntityId(E entity) {
        return Reference.create(entity.getIdentifier(), getDataType(entity));
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends E> getDataType(E entity) {
        return (Class<? extends E>) entity.getClass();
    }

    private static final ObjectSelector<?> INSTANCE = new ObjectSelector<>();

    public static <E extends Entity> ObjectSelector<E> get() {
        return Types.cast(INSTANCE);
    }
}
