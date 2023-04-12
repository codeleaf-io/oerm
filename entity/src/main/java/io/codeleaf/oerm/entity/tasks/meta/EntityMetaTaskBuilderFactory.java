package io.codeleaf.oerm.entity.tasks.meta;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.entity.EntityRecord;
import io.codeleaf.oerm.entity.EntityRepository;
import io.codeleaf.oerm.entity.EntitySchema;
import io.codeleaf.oerm.tasks.meta.MetaTaskBuilderFactory;

public final class EntityMetaTaskBuilderFactory implements MetaTaskBuilderFactory<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {
    @Override
    public DataModelTypes<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> getDataModelTypes() {
        return EntityRepository.GENERIC_TYPES;
    }

    @Override
    public EntitySelectEntityTask.Builder select() {
        return new EntitySelectEntityTask.Builder();
    }

    @Override
    public EntityDetermineDataTypeTask.Builder determine() {
        return new EntityDetermineDataTypeTask.Builder();
    }

    @Override
    public EntityGetDataTypesTask.Builder list() {
        return new EntityGetDataTypesTask.Builder();
    }

    @Override
    public EntityAddDataTypeTask.Builder add() {
        return new EntityAddDataTypeTask.Builder();
    }

    @Override
    public EntityGetEntitySchemaTask.Builder get() {
        return new EntityGetEntitySchemaTask.Builder();
    }

    @Override
    public EntityRemoveDataTypeTask.Builder remove() {
        return new EntityRemoveDataTypeTask.Builder();
    }
}
