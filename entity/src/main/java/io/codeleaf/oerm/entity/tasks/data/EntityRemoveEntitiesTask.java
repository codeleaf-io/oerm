package io.codeleaf.oerm.entity.tasks.data;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.entity.EntityRecord;
import io.codeleaf.oerm.entity.EntityRepository;
import io.codeleaf.oerm.entity.EntitySchema;
import io.codeleaf.oerm.impl.tasks.data.GenericRemoveEntitiesTask;

public final class EntityRemoveEntitiesTask extends GenericRemoveEntitiesTask<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

    public EntityRemoveEntitiesTask(String dataType, Selection selection) {
        super(EntityRepository.GENERIC_TYPES, dataType, selection);
    }

    public static final class Builder extends GenericRemoveEntitiesTask.Builder<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

        public Builder() {
            super(EntityRepository.GENERIC_TYPES);
        }

        @Override
        public EntityRemoveEntitiesTask build() {
            validate();
            return new EntityRemoveEntitiesTask(dataType, selection);
        }
    }
}
