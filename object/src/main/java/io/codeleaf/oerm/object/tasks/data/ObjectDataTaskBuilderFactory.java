package io.codeleaf.oerm.object.tasks.data;

import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.object.Entity;
import io.codeleaf.oerm.object.ObjectRepository;
import io.codeleaf.oerm.object.Reference;
import io.codeleaf.oerm.tasks.data.DataTaskBuilderFactory;

import java.util.function.Supplier;

public final class ObjectDataTaskBuilderFactory implements DataTaskBuilderFactory<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {

    @Override
    public DataModelTypes<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> getDataModelTypes() {
        return ObjectRepository.GENERIC_TYPES;
    }

    @Override
    public ObjectCountEntitiesTask.Builder count() {
        return new ObjectCountEntitiesTask.Builder();
    }

    @Override
    public ObjectCreateEntityTask.Builder create() {
        return new ObjectCreateEntityTask.Builder();
    }

    @Override
    public ObjectAddEntityTask.Builder add() {
        return new ObjectAddEntityTask.Builder();
    }

    @Override
    public ObjectCursorSearchAndCountTask.Builder cursorSearchAndCount() {
        return new ObjectCursorSearchAndCountTask.Builder();
    }

    @Override
    public ObjectCursorSearchTask.Builder cursorSearch() {
        return new ObjectCursorSearchTask.Builder();
    }

    @Override
    public ObjectPageSearchAndCountTask.Builder pageSearchAndCount() {
        return new ObjectPageSearchAndCountTask.Builder();
    }

    @Override
    public ObjectPageSearchTask.Builder pageSearch() {
        return new ObjectPageSearchTask.Builder();
    }

    @Override
    public ObjectRemoveEntitiesTask.Builder remove() {
        return new ObjectRemoveEntitiesTask.Builder();
    }

    @Override
    public ObjectRetrieveEntityTask.Builder retrieve() {
        return new ObjectRetrieveEntityTask.Builder();
    }

    @Override
    public ObjectUpdateFieldsTask.Builder updateFields() {
        return new ObjectUpdateFieldsTask.Builder();
    }

    @Override
    public ObjectUpdateEntityTask.Builder updateEntity() {
        return new ObjectUpdateEntityTask.Builder();
    }
}
