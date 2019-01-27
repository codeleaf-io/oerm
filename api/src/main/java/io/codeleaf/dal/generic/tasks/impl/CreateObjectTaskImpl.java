package io.codeleaf.dal.generic.tasks.impl;

import io.codeleaf.dal.generic.tasks.CreateObjectTask;

import java.util.Objects;

public final class CreateObjectTaskImpl<E, K, D> extends AbstractCreateTask<K, D> implements CreateObjectTask<E, K, D> {

    private final Class<E> objectType;
    private final E object;

    private CreateObjectTaskImpl(Class<E> objectType, D dataType, E object, Class<K> objectIdType) {
        super(objectIdType, dataType);
        this.objectType = objectType;
        this.object = object;
    }

    @Override
    public Class<E> getObjectType() {
        return objectType;
    }

    @Override
    public E getObject() {
        return object;
    }

    public static final class Builder<E, K, D>
            extends AbstractCreateTask.Builder<Builder<E, K, D>, CreateObjectTaskImpl<E, K, D>, K, D>
            implements CreateObjectTask.Builder<Builder<E, K, D>, CreateObjectTaskImpl<E, K, D>, E, K, D> {

        private Class<E> objectType;
        private E object;

        public Builder() {
        }

        public Builder(Class<E> objectType, Class<K> objectTypeId, D dataType) {
            super(objectTypeId, dataType);
            this.objectType = objectType;
        }

        @Override
        public Builder<E, K, D> withObjectType(Class<E> objectType) {
            Objects.requireNonNull(objectType);
            this.objectType = objectType;
            return this;
        }

        @Override
        public Builder<E, K, D> withObject(E object) {
            Objects.requireNonNull(object);
            this.object = object;
            return this;
        }

        protected void validate() {
            super.validate();
            if (object == null) {
                throw new IllegalStateException("Object must be set!");
            }
            if (objectType != null && !objectType.isAssignableFrom(object.getClass())) {
                throw new IllegalStateException("Object must have the specified object type!");
            }
        }

        @Override
        public CreateObjectTaskImpl<E, K, D> build() {
            validate();
            return new CreateObjectTaskImpl<>(objectType, dataType, object, objectIdType);
        }
    }
}
