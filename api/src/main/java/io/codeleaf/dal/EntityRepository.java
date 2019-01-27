package io.codeleaf.dal;

import io.codeleaf.common.utils.MethodReferences;
import io.codeleaf.common.utils.Types;
import io.codeleaf.dal.generic.Repository;
import io.codeleaf.dal.generic.RepositoryTypes;
import io.codeleaf.dal.generic.TypedRepository;
import io.codeleaf.dal.generic.TypedRepositoryImpl;
import io.codeleaf.dal.types.Entity;
import io.codeleaf.dal.types.Reference;

import java.util.Objects;
import java.util.function.Supplier;

public interface EntityRepository<E extends Entity> extends Repository<E, Reference<E>, Class<? extends E>, Supplier<?>, Object> {

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

    default <S extends E> TypedRepository<S, Reference<S>, Class<? extends S>, Supplier<?>, Object> toTypedRepository(Class<S> entityType) {
        return TypedRepositoryImpl.create(Types.cast(this), entityType);
    }
}
