package io.codeleaf.oerm.mapper.entity;

import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.oerm.entity.EntityRecord;

import java.util.Map;

public interface EntityMapper<T, F, V> {

    String getDataType();

    T mapEntity(EntityRecord entityRecord);

    Map<F, V> mapEntityFields(Map<String, ValueWithType<?>> entityFields);
}
