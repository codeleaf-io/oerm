package io.codeleaf.oerm.entity.tasks.data;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.entity.EntityRecord;
import io.codeleaf.oerm.entity.EntityRepository;
import io.codeleaf.oerm.entity.EntitySchema;
import io.codeleaf.oerm.tasks.data.DataTaskBuilderFactory;

public final class EntityDataTaskBuilderFactory implements DataTaskBuilderFactory<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

    @Override
    public DataModelTypes<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> getDataModelTypes() {
        return EntityRepository.GENERIC_TYPES;
    }

    @Override
    public EntityCountEntitiesTask.Builder count() {
        return new EntityCountEntitiesTask.Builder();
    }

    @Override
    public EntityCreateEntityTask.Builder create() {
        return new EntityCreateEntityTask.Builder();
    }

    @Override
    public EntityAddEntityTask.Builder add() {
        return new EntityAddEntityTask.Builder();
    }

    @Override
    public EntityCursorSearchAndCountTask.Builder cursorSearchAndCount() {
        return new EntityCursorSearchAndCountTask.Builder();
    }

    @Override
    public EntityCursorSearchTask.Builder cursorSearch() {
        return new EntityCursorSearchTask.Builder();
    }

    @Override
    public EntityPageSearchAndCountTask.Builder pageSearchAndCount() {
        return new EntityPageSearchAndCountTask.Builder();
    }

    @Override
    public EntityPageSearchTask.Builder pageSearch() {
        return new EntityPageSearchTask.Builder();
    }

    @Override
    public EntityRemoveEntitiesTask.Builder remove() {
        return new EntityRemoveEntitiesTask.Builder();
    }

    @Override
    public EntityRetrieveEntityTask.Builder retrieve() {
        return new EntityRetrieveEntityTask.Builder();
    }

    @Override
    public EntityUpdateFieldsTask.Builder updateFields() {
        return new EntityUpdateFieldsTask.Builder();
    }

    @Override
    public EntityUpdateEntityTask.Builder updateEntity() {
        return new EntityUpdateEntityTask.Builder();
    }
}
