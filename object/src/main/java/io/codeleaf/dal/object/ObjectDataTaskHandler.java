package io.codeleaf.dal.object;

import io.codeleaf.common.utils.MethodReferences;
import io.codeleaf.common.utils.Types;
import io.codeleaf.dal.generic.DataTaskHandler;
import io.codeleaf.dal.generic.RepositoryBridge;

import java.util.function.Supplier;

public interface ObjectDataTaskHandler<E extends Entity> extends DataTaskHandler<E, Reference<E>, Class<? extends E>, Supplier<?>, Object> {

    default <D extends E> D getFieldNames(Class<D> entityType) {
        return MethodReferences.createProxy(entityType);
    }

    default ObjectRepository<E> toRepository() {
        return Types.cast(
                RepositoryBridge.create(getGenericTypes(), this, ObjectIdSelector.get()),
                Types.cast(ObjectRepository.class));
    }
}
