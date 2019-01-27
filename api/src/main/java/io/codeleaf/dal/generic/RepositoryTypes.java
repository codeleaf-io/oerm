package io.codeleaf.dal.generic;

public final class RepositoryTypes<E, K, D, F, V> {

    private final Class<E> objectType;
    private final Class<K> objectIdType;
    private final Class<D> dataTypeType;
    private final Class<F> fieldNameType;
    private final Class<V> fieldValueType;

    public RepositoryTypes(Class<E> objectType, Class<K> objectIdType, Class<D> dataTypeType, Class<F> fieldNameType, Class<V> fieldValueType) {
        this.objectType = objectType;
        this.objectIdType = objectIdType;
        this.dataTypeType = dataTypeType;
        this.fieldNameType = fieldNameType;
        this.fieldValueType = fieldValueType;
    }

    public Class<E> getObjectType() {
        return objectType;
    }

    public Class<K> getObjectIdType() {
        return objectIdType;
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
}
