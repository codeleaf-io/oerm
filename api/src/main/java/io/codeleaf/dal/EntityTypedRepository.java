package io.codeleaf.dal;

import io.codeleaf.common.utils.MethodReferences;
import io.codeleaf.dal.generic.TypedRepository;
import io.codeleaf.dal.types.Entity;
import io.codeleaf.dal.types.Reference;

import java.util.function.Supplier;

public interface EntityTypedRepository<E extends Entity> extends TypedRepository<E, Reference<E>, Class<? extends E>, Supplier<?>, Object> {

    default E getFieldNames() {
        return MethodReferences.createProxy(getDataType());
    }

}
