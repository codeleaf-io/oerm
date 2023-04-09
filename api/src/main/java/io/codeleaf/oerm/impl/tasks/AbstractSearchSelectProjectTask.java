package io.codeleaf.oerm.impl.tasks;

import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.Ordering;
import io.codeleaf.oerm.tasks.ListTask;
import io.codeleaf.oerm.tasks.ProjectTask;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractSearchSelectProjectTask<E, K, D, F, V, S, O>
        extends AbstractSelectTask<E, K, D, F, V, S, O>
        implements ListTask<E, K, D, F, V, S, O>, ProjectTask<E, K, D, F, V, S, O> {

    private final List<Ordering<F>> order;
    private final List<F> projection;

    protected AbstractSearchSelectProjectTask(DataModelTypes<E, K, D, F, V, S> dataModelTypes, Class<O> outputType, D dataType, Selection selection, List<Ordering<F>> order, List<F> projection) {
        super(dataModelTypes, outputType, dataType, selection);
        this.order = order;
        this.projection = projection;
    }

    @Override
    public List<Ordering<F>> getOrder() {
        return order;
    }

    @Override
    public List<F> getProjection() {
        return projection;
    }

    public static abstract class Builder<
            B extends Builder<B, T, E, K, D, F, V, S, O>,
            T extends AbstractSearchSelectProjectTask<E, K, D, F, V, S, O>,
            E, K, D, F, V, S, O>
            extends AbstractSelectTask.Builder<B, T, E, K, D, F, V, S, O>
            implements ListTask.Builder<B, T, E, K, D, F, V, S, O>, ProjectTask.Builder<B, T, E, K, D, F, V, S, O> {

        protected final List<Ordering<F>> order = new LinkedList<>();
        protected final List<F> projection = new LinkedList<>();

        public Builder(DataModelTypes<E, K, D, F, V, S> dataModelTypes) {
            super(dataModelTypes);
        }

        @Override
        public B withOrder(List<Ordering<F>> ordering) {
            Objects.requireNonNull(ordering);
            order.addAll(ordering);
            return Types.cast(this);
        }

        @Override
        public B withProjection(F fieldName) {
            projection.add(fieldName);
            return Types.cast(this);
        }
    }
}
