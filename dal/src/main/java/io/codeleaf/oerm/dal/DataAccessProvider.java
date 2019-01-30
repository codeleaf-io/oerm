package io.codeleaf.oerm.dal;

import io.codeleaf.oerm.entity.EntityDataTaskHandler;
import io.codeleaf.oerm.entity.EntityRepository;
import io.codeleaf.oerm.entity.EntityTypedRepository;
import io.codeleaf.oerm.object.Entity;
import io.codeleaf.oerm.object.ObjectDataTaskHandler;
import io.codeleaf.oerm.object.ObjectRepository;
import io.codeleaf.oerm.object.ObjectTypedRepository;

import java.util.Objects;

public interface DataAccessProvider {

    default ObjectDataTaskHandler<Entity> getObjectDataHandler() {
        return getObjectDataHandler(Entity.class);
    }

    <E extends Entity> ObjectDataTaskHandler<E> getObjectDataHandler(Class<E> entityType);

    default ObjectRepository<Entity> getObjectRepository() {
        return getObjectRepository(Entity.class);
    }

    default <E extends Entity> ObjectRepository<E> getObjectRepository(Class<E> entityType) {
        Objects.requireNonNull(entityType);
        return getObjectDataHandler(entityType).toRepository();
    }

    default <E extends Entity> ObjectTypedRepository<E> getObjectTypedRepository(Class<E> entityType) {
        Objects.requireNonNull(entityType);
        return getObjectRepository(entityType).toTypedRepository(entityType);
    }

    EntityDataTaskHandler getEntityDataHandler();

    default EntityRepository getEntityRepository() {
        return getEntityDataHandler().toRepository(null);
    }

    default EntityTypedRepository getEntityTypedRepository(String dataType) {
        Objects.requireNonNull(dataType);
        return getEntityRepository().toTypedRepository(dataType);
    }
}
