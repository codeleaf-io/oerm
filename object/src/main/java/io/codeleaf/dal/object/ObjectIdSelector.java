package io.codeleaf.dal.object;

import io.codeleaf.common.utils.MethodReferences;
import io.codeleaf.common.utils.Types;
import io.codeleaf.dal.generic.EntityIdSelector;
import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.modeling.selection.SelectionBuilder;

import java.util.function.Supplier;

public final class ObjectIdSelector<E extends Entity> implements EntityIdSelector<Class<? extends E>, Reference<E>> {

    @Override
    public Selection select(Class<? extends E> dataType, Reference<E> objectId) {
        return SelectionBuilder.create(Types.<Class<Supplier<?>>>cast(Supplier.class), Object.class)
                .field(MethodReferences.getProxy(Entity.class)::getIdentifier).equalTo(objectId);
    }

    private static final ObjectIdSelector<?> INSTANCE = new ObjectIdSelector();

    public static <E extends Entity> ObjectIdSelector<E> get() {
        return Types.cast(INSTANCE);
    }
}
