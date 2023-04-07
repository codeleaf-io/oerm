package io.codeleaf.oerm.tasks.impl;

import io.codeleaf.common.utils.Types;
import io.codeleaf.oerm.tasks.ListDataTypesTask;

import java.util.Set;

public final class ListDataTypesTaskImpl<D>
        extends AbstractDataTypeTask<D, Set<D>>
        implements ListDataTypesTask<D> {

    public ListDataTypesTaskImpl(Class<D> dataTypeType) {
        super(dataTypeType, Types.cast(Set.class));
    }

    public static final class Builder<D>
            extends AbstractDataTypeTask.Builder<Builder<D>, ListDataTypesTaskImpl<D>, D, Set<D>>
            implements ListDataTypesTask.Builder<Builder<D>, ListDataTypesTaskImpl<D>, D> {

        public Builder(Class<D> dataTypeType) {
            super(dataTypeType);
        }

        @Override
        public ListDataTypesTaskImpl<D> build() {
            validate();
            return new ListDataTypesTaskImpl<>(dataTypeType);
        }
    }

}
