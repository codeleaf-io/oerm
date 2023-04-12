package io.codeleaf.oerm.entity.tasks.data;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.Ordering;
import io.codeleaf.oerm.entity.EntityRecord;
import io.codeleaf.oerm.entity.EntityRepository;
import io.codeleaf.oerm.entity.EntitySchema;
import io.codeleaf.oerm.impl.tasks.data.GenericCursorSearchAndCountTask;

import java.util.List;

public final class EntityCursorSearchAndCountTask extends GenericCursorSearchAndCountTask<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

    public EntityCursorSearchAndCountTask(String dataType, Selection selection, List<Ordering<String>> order, List<String> projection, int bufferSize) {
        super(EntityRepository.GENERIC_TYPES, dataType, selection, order, projection, bufferSize);
    }

    public static final class Builder extends GenericCursorSearchAndCountTask.Builder<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

        public Builder() {
            super(EntityRepository.GENERIC_TYPES);
        }

        @Override
        public EntityCursorSearchAndCountTask build() {
            validate();
            return new EntityCursorSearchAndCountTask(dataType, selection, order, projection, bufferSize);
        }
    }
}
