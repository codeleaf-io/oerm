package io.codeleaf.dal.tasks.impl;

import io.codeleaf.dal.tasks.UpdateObjectTask;
import io.codeleaf.modeling.selection.Selection;

import java.util.Objects;

public final class UpdateObjectTaskImpl<E, D, F, V> extends AbstractSelectTask<D, F, V, Void> implements UpdateObjectTask<E, D, F, V, Void> {

    private final Class<E> objectType;
    private final E object;

    private UpdateObjectTaskImpl(Selection selection, Class<F> fieldNameType, Class<V> fieldValueType, D dataType, Class<E> objectType, E object) {
        super(selection, fieldNameType, fieldValueType, dataType, Void.class);
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

    public static final class Builder<E, D, F, V>
            extends AbstractSelectTask.Builder<Builder<E, D, F, V>, UpdateObjectTaskImpl<E, D, F, V>, D, F, V, Void>
            implements UpdateObjectTask.Builder<Builder<E, D, F, V>, UpdateObjectTaskImpl<E, D, F, V>, E, D, F, V, Void> {

        private Class<E> objectType;
        private E object;

        public Builder() {
        }

        public Builder(Class<E> objectType, D dataType, Class<F> fieldNameType, Class<V> fieldValueType) {
            super(dataType, fieldNameType, fieldValueType);
            this.objectType = objectType;
        }

        @Override
        public Builder<E, D, F, V> withObjectType(Class<E> objectType) {
            Objects.requireNonNull(objectType);
            this.objectType = objectType;
            return this;
        }

        @Override
        public Builder<E, D, F, V> withObject(E object) {
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

        @SuppressWarnings("unchecked")
        @Override
        public UpdateObjectTaskImpl<E, D, F, V> build() {
            validate();
            return new UpdateObjectTaskImpl<>(selection, fieldNameType, fieldValueType, dataType,
                    objectType == null ? (Class<E>) object.getClass() : objectType,
                    object);
        }
    }
}
