package io.codeleaf.oerm.generic;

public final class RepositoryTypes<E, K, D, F, V, S> {

    private final Class<E> entityType;
    private final Class<K> entityIdType;
    private final Class<D> dataTypeType;
    private final Class<F> fieldNameType;
    private final Class<V> fieldValueType;
    private final Class<S> entitySchemaType;

    public RepositoryTypes(Class<E> entityType, Class<K> entityIdType, Class<D> dataTypeType, Class<F> fieldNameType, Class<V> fieldValueType, Class<S> entitySchemaType) {
        this.entityType = entityType;
        this.entityIdType = entityIdType;
        this.dataTypeType = dataTypeType;
        this.fieldNameType = fieldNameType;
        this.fieldValueType = fieldValueType;
        this.entitySchemaType = entitySchemaType;
    }

    public Class<E> getEntityType() {
        return entityType;
    }

    public Class<K> getEntityIdType() {
        return entityIdType;
    }

    public Class<D> getDataTypeType() {
        return dataTypeType;
    }

    public Class<F> getFieldNameType() {
        return fieldNameType;
    }

    public Class<V> getFieldValueType() {
        return fieldValueType;
    }

    public Class<S> getEntitySchemaType() {
        return entitySchemaType;
    }
}
