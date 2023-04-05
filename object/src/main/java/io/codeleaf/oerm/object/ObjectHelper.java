package io.codeleaf.oerm.object;

import io.codeleaf.common.utils.MethodReferences;
import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.modeling.selection.SelectionBuilder;
import io.codeleaf.oerm.generic.EntityHelper;

import java.util.function.Supplier;

public final class ObjectHelper<E extends Entity> implements EntityHelper<E, Reference<E>, Class<? extends E>> {

    @Override
    public Selection select(Class<? extends E> dataType, Reference<E> objectId) {
        return SelectionBuilder.create(Types.<Class<Supplier<?>>>cast(Supplier.class), Object.class)
                .field(MethodReferences.getProxy(Entity.class)::getIdentifier).equalTo(objectId);
    }

    @Override
    public Class<? extends E> getDataType(E entity) {
        return Types.cast(entity.getMeta().getEntityType());
    }

    private static final ObjectHelper<?> INSTANCE = new ObjectHelper<>();

    public static <E extends Entity> ObjectHelper<E> get() {
        return Types.cast(INSTANCE);
    }
}
