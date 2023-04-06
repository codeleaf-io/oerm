package io.codeleaf.oerm.generic.tasks.impl;

import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.generic.tasks.SelectEntityTask;

public final class SelectEntityTaskImpl<K, D> extends AbstractDataTypeTask<D, Selection>
        implements SelectEntityTask<K, D> {

    private final D dataType;
    private final K entityId;
    private final Class<K> entityIdType;

    public SelectEntityTaskImpl(Class<D> dataTypeType, D dataType, K entityId, Class<K> entityIdType) {
        super(dataTypeType, Selection.class);
        this.dataType = dataType;
        this.entityId = entityId;
        this.entityIdType = entityIdType;
    }

    @Override
    public D getDataType() {
        return dataType;
    }

    @Override
    public K getEntityId() {
        return entityId;
    }

    @Override
    public Class<K> getEntityIdType() {
        return entityIdType;
    }

    public static final class Builder<K, D>
            extends AbstractDataTypeTask.Builder<Builder<K, D>, SelectEntityTaskImpl<K, D>, D, Selection>
            implements SelectEntityTask.Builder<Builder<K, D>, SelectEntityTaskImpl<K, D>, K, D> {

        private final Class<K> entityIdType;
        private D dataType;
        private K entityId;

        public Builder(Class<K> entityIdType, Class<D> dataTypeType) {
            super(dataTypeType);
            this.entityIdType = entityIdType;
        }

        @Override
        public Builder<K, D> withDataType(D dataType) {
            this.dataType = dataType;
            return this;
        }

        @Override
        public Builder<K, D> withEntityId(K entityId) {
            this.entityId = entityId;
            return this;
        }

        @Override
        protected void validate() {
            super.validate();
            if (!dataTypeType.isInstance(dataType)) {
                throw new IllegalStateException();
            }
            if (entityIdType == null || !entityIdType.isInstance(entityId)) {
                throw new IllegalStateException();
            }
        }

        @Override
        public SelectEntityTaskImpl<K, D> build() {
            validate();
            return new SelectEntityTaskImpl<>(dataTypeType, dataType, entityId, entityIdType);
        }
    }

}
