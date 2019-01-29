package io.codeleaf.dal.object;

import io.codeleaf.common.utils.MethodReferences;
import io.codeleaf.dal.generic.TypedRepository;

import java.util.function.Supplier;

public interface ObjectTypedRepository<E extends Entity> extends TypedRepository<E, Reference<E>, Class<? extends E>, Supplier<?>, Object> {

    default E getFieldNames() {
        return MethodReferences.createProxy(getDataType());
    }

}
