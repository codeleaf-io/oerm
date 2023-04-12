package io.codeleaf.oerm.entity.tasks.data;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.entity.EntityRecord;
import io.codeleaf.oerm.entity.EntityRepository;
import io.codeleaf.oerm.entity.EntitySchema;
import io.codeleaf.oerm.impl.tasks.data.GenericRetrieveEntityTask;

public final class EntityRetrieveEntityTask extends GenericRetrieveEntityTask<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

    public EntityRetrieveEntityTask(String dataType, Selection selection) {
        super(EntityRepository.GENERIC_TYPES, dataType, selection);
    }

    public static final class Builder extends GenericRetrieveEntityTask.Builder<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

        public Builder() {
            super(EntityRepository.GENERIC_TYPES);
        }

        @Override
        public EntityRetrieveEntityTask build() {
            validate();
            return new EntityRetrieveEntityTask(dataType, selection);
        }
    }
}
