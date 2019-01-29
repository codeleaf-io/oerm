package io.codeleaf.dal.object;

import io.codeleaf.common.utils.Interceptor;
import io.codeleaf.common.utils.MethodReferences;
import io.codeleaf.common.utils.Types;
import io.codeleaf.dal.generic.Repository;
import io.codeleaf.dal.generic.RepositoryTypes;
import io.codeleaf.dal.generic.TypedRepositoryImpl;

import java.util.Objects;
import java.util.function.Supplier;

public interface ObjectRepository<E extends Entity> extends Repository<E, Reference<E>, Class<? extends E>, Supplier<?>, Object> {

    default <D extends E> D getFieldNames(Class<D> entityType) {
        return MethodReferences.createProxy(entityType);
    }

    static <E extends Entity> RepositoryTypes<E, Reference<E>, Class<? extends E>, Supplier<?>, Object> createRepositoryTypes(Class<E> entityType) {
        Objects.requireNonNull(entityType);
        return new RepositoryTypes<>(
                entityType,
                Types.cast(Reference.class),
                Types.cast(Class.class),
                Types.cast(Supplier.class),
                Object.class);
    }

    default <S extends E> ObjectTypedRepository<S> toTypedRepository(Class<S> entityType) {
        RepositoryTypes<S, Reference<S>, Class<? extends S>, Supplier<?>, Object> repositoryTypes = createRepositoryTypes(entityType);
        ObjectRepository<S> objectRepository = Interceptor.create(Types.cast(ObjectRepository.class), this);
        Interceptor.intercept(objectRepository, "getGenericTypes", null, (args) -> repositoryTypes);
        return Types.cast(
                TypedRepositoryImpl.create(objectRepository, entityType),
                Types.cast(ObjectTypedRepository.class));
    }
}
