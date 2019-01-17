package io.codeleaf.dal.tasks;

import io.codeleaf.common.utils.MethodReferences;
import io.codeleaf.dal.types.Entity;
import io.codeleaf.dal.types.Reference;
import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.modeling.selection.builder.SelectableBuilder;

import java.util.Objects;
import java.util.function.Supplier;

public final class EntityRetrieveTask<E extends Entity> implements EntityTask<E, Class<E>> {

    public static final class Builder<E extends Entity> implements SelectableBuilder<Supplier<?>, Object, Builder<E>> {

        private Selection selection;
        private Class<E> entityType;

        public Builder(Class<E> entityType) {
            Objects.requireNonNull(entityType);
            if (!Entity.class.isAssignableFrom(entityType)) {
                throw new IllegalArgumentException();
            }
            this.entityType = entityType;
        }

        @Override
        public void select(Selection selection) {
            this.selection = selection;
        }

        public Builder<E> withSelection(Selection selection) {
            this.selection = selection;
            return this;
        }

        public EntityRetrieveTask<E> build() {
            Objects.requireNonNull(entityType);
            Objects.requireNonNull(selection);
            return new EntityRetrieveTask<>(entityType, selection);
        }

    }

    public static <E extends Entity> EntityRetrieveTask<E> create(Reference<E> reference) {
        Objects.requireNonNull(reference);
        return new Builder<>(reference.getEntityType())
                .beginSelection()
                .field(MethodReferences.getProxy(Entity.class)::getIdentifier).equalTo(reference.getIdentifier()).endSelection()
                .build();
    }

    private final Class<E> entityType;
    private final Selection selection;

    public EntityRetrieveTask(Class<E> entityType, Selection selection) {
        this.entityType = entityType;
        this.selection = selection;
    }

    @Override
    public Class<E> getOutputType() {
        return entityType;
    }

    @Override
    public Class<E> getEntityType() {
        return entityType;
    }

    public Selection getSelection() {
        return selection;
    }

}
