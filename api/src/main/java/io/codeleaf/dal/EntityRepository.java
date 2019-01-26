package io.codeleaf.dal;

import io.codeleaf.dal.types.Entity;
import io.codeleaf.dal.types.Reference;

import java.util.Objects;
import java.util.function.Supplier;

public interface EntityRepository<E extends Entity> extends Repository<E, Reference<E>, Class<?>, Supplier<?>, Object, E> {

    @SuppressWarnings("unchecked")
    static <E extends Entity> RepositoryType<E, Reference<E>, Class<?>, Supplier<?>, Object, E> createRepositoryType(Class<E> entityType) {
        Objects.requireNonNull(entityType);
        return new RepositoryType<>(
                entityType,
                (Class<Reference<E>>) (Class) Reference.class,
                (Class<Class<?>>) (Class) Class.class,
                (Class<Supplier<?>>) (Class) Supplier.class,
                Object.class,
                entityType);
    }

    static <E extends Entity> TaskBuilderFactory<E, Reference<E>, Class<?>, Supplier<?>, Object, E> createTaskBuilderFactory(Class<E> entityType) {
        return TaskBuilderFactory.create(createRepositoryType(entityType));
    }

    static <E extends Entity, S extends E> TypedRepository<E, Reference<E>, Class<?>, Supplier<?>, Object, E> typeRepository(EntityRepository<E> entityRepository, Class<S> dataType) {
        if (!entityRepository.getRepositoryType().getDataTypeType().isAssignableFrom(dataType)) {
            throw new IllegalArgumentException();
        }
        return TypedRepository.create(entityRepository, dataType);
    }
}
