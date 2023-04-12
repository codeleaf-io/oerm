package io.codeleaf.oerm.entity.tasks.data;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.ValueWithType;
import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.Ordering;
import io.codeleaf.oerm.entity.EntityRecord;
import io.codeleaf.oerm.entity.EntityRepository;
import io.codeleaf.oerm.entity.EntitySchema;
import io.codeleaf.oerm.impl.tasks.data.GenericPageSearchAndCountTask;

import java.util.List;

public final class EntityPageSearchAndCountTask extends GenericPageSearchAndCountTask<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

    public EntityPageSearchAndCountTask(String dataType, Selection selection, List<Ordering<String>> order, List<String> projection, long offset, int limit) {
        super(EntityRepository.GENERIC_TYPES, dataType, selection, order, projection, offset, limit);
    }

    public static final class Builder extends GenericPageSearchAndCountTask.Builder<EntityRecord, IdentifierWithType, String, String, ValueWithType<?>, EntitySchema> {

        public Builder() {
            super(EntityRepository.GENERIC_TYPES);
        }

        @Override
        public EntityPageSearchAndCountTask build() {
            validate();
            return new EntityPageSearchAndCountTask(dataType, selection, order, projection, offset, limit);
        }
    }
}
