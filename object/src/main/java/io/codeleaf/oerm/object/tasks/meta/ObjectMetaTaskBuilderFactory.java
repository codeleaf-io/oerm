package io.codeleaf.oerm.object.tasks.meta;

import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.object.Entity;
import io.codeleaf.oerm.object.ObjectRepository;
import io.codeleaf.oerm.object.Reference;
import io.codeleaf.oerm.tasks.meta.MetaTaskBuilderFactory;

import java.util.function.Supplier;

public final class ObjectMetaTaskBuilderFactory implements MetaTaskBuilderFactory<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {
    @Override
    public DataModelTypes<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> getDataModelTypes() {
        return ObjectRepository.GENERIC_TYPES;
    }

    @Override
    public ObjectSelectEntityTask.Builder select() {
        return new ObjectSelectEntityTask.Builder();
    }

    @Override
    public ObjectDetermineDataTypeTask.Builder determine() {
        return new ObjectDetermineDataTypeTask.Builder();
    }

    @Override
    public ObjectGetDataTypesTask.Builder list() {
        return new ObjectGetDataTypesTask.Builder();
    }

    @Override
    public ObjectAddDataTypeTask.Builder add() {
        return new ObjectAddDataTypeTask.Builder();
    }

    @Override
    public ObjectGetEntitySchemaTask.Builder get() {
        return new ObjectGetEntitySchemaTask.Builder();
    }

    @Override
    public ObjectRemoveDataTypeTask.Builder remove() {
        return new ObjectRemoveDataTypeTask.Builder();
    }
}
