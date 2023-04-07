package io.codeleaf.oerm.object;

import io.codeleaf.common.utils.Interceptor;
import io.codeleaf.common.utils.MethodReferences;
import io.codeleaf.common.utils.Types;
import io.codeleaf.oerm.Repository;
import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.impl.TypedRepositoryImpl;

import java.util.function.Supplier;

public interface ObjectRepository extends Repository<Entity, Reference<Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {

    DataModelTypes<Entity, Reference<Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> GENERIC_TYPES = new DataModelTypes<>(
            Entity.class,
            Types.cast(Reference.class),
            Types.cast(Class.class),
            Types.cast(Supplier.class),
            Object.class,
            Types.cast(Class.class));

    @Override
    default DataModelTypes<Entity, Reference<Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> getDataModelTypes() {
        return GENERIC_TYPES;
    }

    default <D extends Entity> D getFieldNames(Class<D> entityType) {
        return MethodReferences.createProxy(entityType);
    }

    default <E extends Entity> ObjectTypedRepository<E> toTypedRepository(Class<E> entityType) {
        DataModelTypes<E, Reference<E>, Class<? extends E>, Supplier<?>, Object, Class<E>> repositoryTypes = new DataModelTypes<>(
                entityType,
                Types.cast(Reference.class),
                Types.cast(Class.class),
                Types.cast(Supplier.class),
                Object.class,
                Types.cast(entityType));
        ObjectTypedRepository<E> interceptor = Interceptor.create(
                Types.cast(ObjectTypedRepository.class),
                TypedRepositoryImpl.create(this, entityType));
        Interceptor.intercept(interceptor, "getGenericTypes", null, (args) -> repositoryTypes);
        return interceptor;
    }
}
