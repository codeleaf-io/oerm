package io.codeleaf.oerm.record;

import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.RecordType;
import io.codeleaf.modeling.data.RecordWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.impl.DatabaseTaskHandler;
import io.codeleaf.oerm.impl.DefaultRepository;
import io.codeleaf.oerm.record.tasks.data.RecordDataTaskBuilderFactory;
import io.codeleaf.oerm.record.tasks.meta.RecordMetaTaskBuilderFactory;

public final class RecordRepository
        extends DefaultRepository<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> {

    public static final DataModelTypes<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> GENERIC_TYPES = new DataModelTypes<>(
            RecordWithType.class,
            IdentifierWithType.class,
            String.class,
            String.class,
            Types.cast(ValueWithType.class),
            RecordType.class);

    public static final RecordDataTaskBuilderFactory DATA_TASK_BUILDER_FACTORY = new RecordDataTaskBuilderFactory();

    public static final RecordMetaTaskBuilderFactory META_TASK_BUILDER_FACTORY = new RecordMetaTaskBuilderFactory();

    private RecordRepository(RecordDataTaskBuilderFactory dataTaskBuilderFactory, RecordMetaTaskBuilderFactory metaTaskBuilderFactory, DatabaseTaskHandler<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> databaseTaskHandler) {
        super(dataTaskBuilderFactory, metaTaskBuilderFactory, databaseTaskHandler);
    }

    public static RecordRepository of(DatabaseTaskHandler<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType> databaseTaskHandler) {
        return new RecordRepository(DATA_TASK_BUILDER_FACTORY, META_TASK_BUILDER_FACTORY, databaseTaskHandler);
    }

    public static DatabaseTaskHandler.Builder<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordType, RecordRepository> builder() {
        return DatabaseTaskHandler.Builder.create(GENERIC_TYPES, RecordRepository::of);
    }
}