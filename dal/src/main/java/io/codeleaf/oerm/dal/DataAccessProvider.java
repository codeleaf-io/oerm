package io.codeleaf.oerm.dal;

import io.codeleaf.oerm.dal.impl.DataGovernor;
import io.codeleaf.oerm.dal.impl.DataProvider;
import io.codeleaf.oerm.dal.impl.EntityObjectDataTaskHandler;
import io.codeleaf.oerm.dal.store.InMemoryRecordStore;
import io.codeleaf.oerm.entity.EntityDataTaskHandler;
import io.codeleaf.oerm.entity.EntityRepository;
import io.codeleaf.oerm.entity.EntityTypedRepository;
import io.codeleaf.oerm.object.Entity;
import io.codeleaf.oerm.object.ObjectDataTaskHandler;
import io.codeleaf.oerm.object.ObjectRepository;
import io.codeleaf.oerm.object.ObjectTypedRepository;

import java.util.Objects;

public final class DataAccessProvider {

    private final ObjectDataTaskHandler objectHandler;
    private final EntityDataTaskHandler entityHandler;

    private DataAccessProvider(ObjectDataTaskHandler objectHandler, EntityDataTaskHandler entityHandler) {
        this.objectHandler = objectHandler;
        this.entityHandler = entityHandler;
    }

    public ObjectDataTaskHandler getObjectDataHandler() {
        return objectHandler;
    }

    public ObjectRepository getObjectRepository() {
        return getObjectDataHandler().toRepository();
    }

    public <E extends Entity> ObjectTypedRepository<E> getObjectTypedRepository(Class<E> entityType) {
        Objects.requireNonNull(entityType);
        return getObjectRepository().toTypedRepository(entityType);
    }

    public EntityDataTaskHandler getEntityDataHandler() {
        return entityHandler;
    }

    public EntityRepository getEntityRepository() {
        return getEntityDataHandler().toRepository(null);
    }

    public EntityTypedRepository getEntityTypedRepository(String dataType) {
        Objects.requireNonNull(dataType);
        return getEntityRepository().toTypedRepository(dataType);
    }

    private static final DataAccessProvider INSTANCE = create();

    public static DataAccessProvider get() {
        return INSTANCE;
    }

    private static DataAccessProvider create() {
        EntityDataTaskHandler entityHandler = new DataGovernor(new DataProvider(new InMemoryRecordStore()));
        return new DataAccessProvider(new EntityObjectDataTaskHandler(entityHandler), entityHandler);
    }
}
