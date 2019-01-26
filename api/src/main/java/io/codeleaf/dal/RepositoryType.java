package io.codeleaf.dal;

public final class RepositoryType<E, K, D, F, V, H> {

    private final Class<E> objectType;
    private final Class<K> objectIdType;
    private final Class<D> dataTypeType;
    private final Class<F> fieldNameType;
    private final Class<V> fieldValueType;
    private final Class<H> searchHitType;

    public RepositoryType(Class<E> objectType, Class<K> objectIdType, Class<D> dataTypeType, Class<F> fieldNameType, Class<V> fieldValueType, Class<H> searchHitType) {
        this.objectType = objectType;
        this.objectIdType = objectIdType;
        this.dataTypeType = dataTypeType;
        this.fieldNameType = fieldNameType;
        this.fieldValueType = fieldValueType;
        this.searchHitType = searchHitType;
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

    public Class<H> getSearchHitType() {
        return searchHitType;
    }
}
