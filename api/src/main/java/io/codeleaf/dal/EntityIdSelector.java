package io.codeleaf.dal;

import io.codeleaf.common.utils.MethodReferences;
import io.codeleaf.common.utils.Types;
import io.codeleaf.dal.generic.ObjectIdSelector;
import io.codeleaf.dal.types.Entity;
import io.codeleaf.dal.types.Reference;
import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.modeling.selection.SelectionBuilder;

import java.util.function.Supplier;

public final class EntityIdSelector<E extends Entity> implements ObjectIdSelector<Class<? extends E>, Reference<E>> {

    @Override
    public Selection select(Class<? extends E> dataType, Reference<E> objectId) {
        return SelectionBuilder.create(Types.<Class<Supplier<?>>>cast(Supplier.class), Object.class)
                .field(MethodReferences.getProxy(Entity.class)::getIdentifier).equalTo(objectId);
    }

    private static final EntityIdSelector<?> INSTANCE = new EntityIdSelector();

    public static <E extends Entity> EntityIdSelector<E> get() {
        return Types.cast(INSTANCE);
    }
}
