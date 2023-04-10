package io.codeleaf.oerm.object;

import io.codeleaf.common.utils.MethodReferences;
import io.codeleaf.common.utils.Types;
import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.impl.DatabaseTaskHandler;
import io.codeleaf.oerm.impl.DefaultRepository;
import io.codeleaf.oerm.object.tasks.data.ObjectDataTaskBuilderFactory;
import io.codeleaf.oerm.object.tasks.meta.ObjectMetaTaskBuilderFactory;

import java.util.function.Supplier;

public final class ObjectRepository
        extends DefaultRepository<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> {

    public static final DataModelTypes<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> GENERIC_TYPES = new DataModelTypes<>(
            Entity.class,
            Types.cast(Reference.class),
            Types.cast(Class.class),
            Types.cast(Supplier.class),
            Object.class,
            Types.cast(Class.class));

    public <E extends Entity> E getFieldNames(Class<E> dataType) {
        return MethodReferences.createProxy(dataType);
    }

    private ObjectRepository(DatabaseTaskHandler<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> databaseTaskHandler) {
        super(new ObjectDataTaskBuilderFactory(), new ObjectMetaTaskBuilderFactory(), databaseTaskHandler);
    }

    public static ObjectRepository of(DatabaseTaskHandler<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> databaseTaskHandler) {
        return new ObjectRepository(databaseTaskHandler);
    }

    public static DatabaseTaskHandler.Builder<Entity, Reference<? extends Entity>, Class<? extends Entity>, Supplier<?>, Object, Class<? extends Entity>> createTaskHandler() {
        return new DatabaseTaskHandler.Builder<>(GENERIC_TYPES);
    }
}