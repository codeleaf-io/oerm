package io.codeleaf.oerm.entity.tasks.data;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.entity.EntityRecord;
import io.codeleaf.oerm.entity.EntityRepository;
import io.codeleaf.oerm.entity.EntitySchema;
import io.codeleaf.oerm.impl.tasks.data.GenericUpdateFieldsTask;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class EntityUpdateFieldsTask extends GenericUpdateFieldsTask<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

    public EntityUpdateFieldsTask(String dataType, Selection selection, Map<String, ValueWithType<?>> fields) {
        super(EntityRepository.GENERIC_TYPES, dataType, selection, fields);
    }

    public static final class Builder extends GenericUpdateFieldsTask.Builder<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

        public Builder() {
            super(EntityRepository.GENERIC_TYPES);
        }

        @Override
        public EntityUpdateFieldsTask build() {
            validate();
            return new EntityUpdateFieldsTask(dataType, selection, Collections.unmodifiableMap(new LinkedHashMap<>(fields)));
        }
    }
}
