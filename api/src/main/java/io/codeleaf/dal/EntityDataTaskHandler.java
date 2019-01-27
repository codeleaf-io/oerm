package io.codeleaf.dal;

import io.codeleaf.common.utils.Types;
import io.codeleaf.dal.generic.DataTaskHandler;
import io.codeleaf.dal.generic.RepositoryBridge;
import io.codeleaf.dal.types.Entity;
import io.codeleaf.dal.types.Reference;

import java.util.function.Supplier;

public interface EntityDataTaskHandler<E extends Entity> extends DataTaskHandler<E, Reference<E>, Class<? extends E>, Supplier<?>, Object> {

    default EntityRepository<E> toRepository() {
        return Types.cast(
                RepositoryBridge.create(getGenericTypes(), this, EntityIdSelector.get()),
                Types.cast(EntityRepository.class));
    }
}
