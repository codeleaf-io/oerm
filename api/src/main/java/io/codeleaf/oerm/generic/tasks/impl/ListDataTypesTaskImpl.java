package io.codeleaf.oerm.generic.tasks.impl;

import io.codeleaf.common.utils.Types;
import io.codeleaf.oerm.generic.tasks.ListDataTypesTask;

import java.util.Set;

public final class ListDataTypesTaskImpl<D>
        extends AbstractDataTypeTask<D, Set<D>>
        implements ListDataTypesTask<D> {

    public ListDataTypesTaskImpl(Class<Set<D>> outputType) {
        super(outputType);
    }

    public static final class Builder<D>
            extends AbstractDataTypeTask.Builder<Builder<D>, ListDataTypesTaskImpl<D>, D, Set<D>>
            implements ListDataTypesTask.Builder<Builder<D>, ListDataTypesTaskImpl<D>, D> {

        private final Class<D> dataTypeClass;

        public Builder(Class<D> dataTypeClass) {
            this.dataTypeClass = dataTypeClass;
        }

        @Override
        protected void validate() {
            super.validate();
            if (dataTypeClass == null) {
                throw new IllegalStateException();
            }
        }

        @Override
        public ListDataTypesTaskImpl<D> build() {
            validate();
            return new ListDataTypesTaskImpl<>(Types.cast(Set.class));
        }
    }

}
