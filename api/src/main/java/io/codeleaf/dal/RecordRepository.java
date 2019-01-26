package io.codeleaf.dal;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.RecordWithType;
import io.codeleaf.modeling.data.ValueWithType;

public interface RecordRepository extends Repository<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordWithType> {

    @SuppressWarnings("unchecked")
    RepositoryType<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordWithType> repositoryType = new RepositoryType<>(
            RecordWithType.class,
            IdentifierWithType.class,
            String.class,
            String.class,
            (Class<ValueWithType<?>>) (Class) ValueWithType.class,
            RecordWithType.class);

    @Override
    default RepositoryType<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordWithType> getRepositoryType() {
        return repositoryType;
    }

    TaskBuilderFactory<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordWithType> taskBuilders = TaskBuilderFactory.create(repositoryType);

    static TypedRepository<RecordWithType, IdentifierWithType, String, String, ValueWithType<?>, RecordWithType> typeRepository(RecordRepository recordRepository, String dataType) {
        return TypedRepository.create(recordRepository, dataType);
    }
}
