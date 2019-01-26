package io.codeleaf.dal.tasks.impl;

import io.codeleaf.dal.Ordering;
import io.codeleaf.dal.tasks.ProjectTask;
import io.codeleaf.dal.tasks.SearchTask;
import io.codeleaf.modeling.selection.Selection;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractSearchSelectProjectTask<D, F, V, H, O> extends AbstractSelectTask<D, F, V, O> implements SearchTask<D, F, H, O>, ProjectTask<D, F, O> {

    private final List<F> projection;
    private final Class<H> searchHitType;
    private final List<Ordering<F>> order;

    protected AbstractSearchSelectProjectTask(Class<H> searchHitType, Selection selection, Class<F> fieldNameType, Class<V> fieldValueType, D dataType, Class<O> outputType, List<F> projection, List<Ordering<F>> order) {
        super(selection, fieldNameType, fieldValueType, dataType, outputType);
        this.projection = projection;
        this.searchHitType = searchHitType;
        this.order = order;
    }

    @Override
    public List<F> getProjection() {
        return projection;
    }

    @Override
    public Class<H> getSearchHitType() {
        return searchHitType;
    }

    @Override
    public List<Ordering<F>> getOrder() {
        return order;
    }

    @SuppressWarnings("unchecked")
    public static abstract class Builder<
            B extends Builder<B, T, D, F, V, H, O>,
            T extends AbstractSearchSelectProjectTask<D, F, V, H, O>,
            D,
            F,
            V,
            H,
            O
            > extends AbstractSelectTask.Builder<B, T, D, F, V, O>
            implements SearchTask.Builder<B, T, D, F, H, O>, ProjectTask.Builder<B, T, D, F, O> {

        protected final List<F> projection = new LinkedList<>();
        protected final List<Ordering<F>> order = new LinkedList<>();
        protected Class<H> searchHitType;

        public Builder() {
        }

        public Builder(D dataType, Class<F> fieldNameType, Class<V> fieldValueType, Class<H> searchHitType) {
            super(dataType, fieldNameType, fieldValueType);
            this.searchHitType = searchHitType;
        }

        @Override
        public B withProjection(F fieldName) {
            projection.add(fieldName);
            return (B) this;
        }

        @Override
        public B withSearchHitType(Class<H> searchHitType) {
            this.searchHitType = searchHitType;
            return (B) this;
        }

        @Override
        public B withOrder(Ordering<F>... ordering) {
            Objects.requireNonNull(ordering);
            order.addAll(Arrays.asList(ordering));
            return (B) this;
        }

        protected void validate() {
            super.validate();
            if (projection.isEmpty() || searchHitType == null) {
                throw new IllegalStateException();
            }
        }
    }
}
