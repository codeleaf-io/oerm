package io.codeleaf.oerm.entity;

import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.entity.tasks.data.EntityDataTaskBuilderFactory;
import io.codeleaf.oerm.entity.tasks.meta.EntityMetaTaskBuilderFactory;
import io.codeleaf.oerm.impl.DatabaseTaskHandler;
import io.codeleaf.oerm.impl.DefaultRepository;

public final class EntityRepository
        extends DefaultRepository<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

    public static final DataModelTypes<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> GENERIC_TYPES = new DataModelTypes<>(
            EntityRecord.class,
            IdentifierWithType.class,
            String.class,
            String.class,
            Types.cast(ValueWithType.class),
            EntitySchema.class);

    public static final EntityDataTaskBuilderFactory DATA_TASK_BUILDER_FACTORY = new EntityDataTaskBuilderFactory();

    public static final EntityMetaTaskBuilderFactory META_TASK_BUILDER_FACTORY = new EntityMetaTaskBuilderFactory();

    private EntityRepository(EntityDataTaskBuilderFactory dataTaskBuilderFactory, EntityMetaTaskBuilderFactory metaTaskBuilderFactory, DatabaseTaskHandler<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> databaseTaskHandler) {
        super(dataTaskBuilderFactory, metaTaskBuilderFactory, databaseTaskHandler);
    }

    public static EntityRepository of(DatabaseTaskHandler<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> databaseTaskHandler) {
        return new EntityRepository(DATA_TASK_BUILDER_FACTORY, META_TASK_BUILDER_FACTORY, databaseTaskHandler);
    }

    public static DatabaseTaskHandler.Builder<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema, EntityRepository> builder() {
        return DatabaseTaskHandler.Builder.create(GENERIC_TYPES, EntityRepository::of);
    }
}