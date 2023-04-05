package io.codeleaf.oerm.object;

import io.codeleaf.common.utils.MethodReferences;
import io.codeleaf.common.utils.Types;
import io.codeleaf.oerm.generic.DataTaskHandler;
import io.codeleaf.oerm.generic.RepositoryBridge;
import io.codeleaf.oerm.generic.RepositoryTypes;

import java.util.function.Supplier;

public interface ObjectDataTaskHandler extends DataTaskHandler<Entity, Reference<Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {

    @Override
    default RepositoryTypes<Entity, Reference<Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> getGenericTypes() {
        return ObjectRepository.GENERIC_TYPES;
    }

    default <D extends Entity> D getFieldNames(Class<D> entityType) {
        return MethodReferences.createProxy(entityType);
    }

    default ObjectRepository toRepository() {
        return Types.cast(
                RepositoryBridge.create(getGenericTypes(), this),
                ObjectRepository.class);
    }
}
