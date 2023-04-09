package io.codeleaf.oerm.dal;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.entity.EntityRecord;
import io.codeleaf.oerm.entity.EntityRepository;
import io.codeleaf.oerm.entity.EntitySchema;
import io.codeleaf.oerm.impl.TypedRepositoryImpl;
import io.codeleaf.oerm.object.Entity;
import io.codeleaf.oerm.object.ObjectRepository;
import io.codeleaf.oerm.object.Reference;

import java.util.Objects;
import java.util.function.Supplier;

public final class DataAccessProvider {

    private final ObjectRepository objectRepository;
    private final EntityRepository entityRepository;
    private final DataTypeRegistry dataTypeRegistry;

    private DataAccessProvider(ObjectRepository objectRepository, EntityRepository entityRepository, DataTypeRegistry dataTypeRegistry) {
        this.objectRepository = objectRepository;
        this.entityRepository = entityRepository;
        this.dataTypeRegistry = dataTypeRegistry;
    }

    public ObjectRepository getObjectRepository() {
        return objectRepository;
    }

    public <E extends Entity> TypedRepositoryImpl<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> getTypedRepository(Class<E> dataType) {
        Objects.requireNonNull(dataType);
        return TypedRepositoryImpl.create(objectRepository, dataType);
    }

    public EntityRepository getEntityRepository() {
        return entityRepository;
    }

    public TypedRepositoryImpl<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> getTypedRepository(String dataType) {
        Objects.requireNonNull(dataType);
        return TypedRepositoryImpl.create(entityRepository, dataType);
    }

    public DataTypeRegistry getDataTypeRegistry() {
        return dataTypeRegistry;
    }

    private static final DataAccessProvider INSTANCE = create();

    public static DataAccessProvider get() {
        return INSTANCE;
    }

    private static DataAccessProvider create() {
//        EntityDataTaskHandler entityHandler = new DataGovernor(new DataProvider(new InMemoryRecordStore()));
//        DataTypeRegistry dataTypeRegistry = new DataTypeMapper();
//        return new DataAccessProvider(new EntityObjectDataTaskHandler(entityHandler), entityHandler, dataTypeRegistry);
        return null;
    }
}
