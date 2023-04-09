package io.codeleaf.oerm.impl.tasks;

import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.Ordering;
import io.codeleaf.oerm.SearchPage;
import io.codeleaf.oerm.tasks.data.PageSearchTask;

import java.util.List;

public abstract class AbstractPageSearchTask<E, K, D, F, V, S, O extends SearchPage<E>>
        extends AbstractSearchSelectProjectTask<E, K, D, F, V, S, O>
        implements PageSearchTask<E, K, D, F, V, S, O> {

    private final long offset;
    private final int limit;

    protected AbstractPageSearchTask(DataModelTypes<E, K, D, F, V, S> dataModelTypes, D dataType, Selection selection, List<Ordering<F>> order, List<F> projection, long offset, int limit) {
        super(dataModelTypes, Types.cast(SearchPage.class), dataType, selection, order, projection);
        this.offset = offset;
        this.limit = limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public int getLimit() {
        return limit;
    }

    public static abstract class Builder<
            B extends Builder<B, T, E, K, D, F, V, S, O>,
            T extends AbstractPageSearchTask<E, K, D, F, V, S, O>,
            E, K, D, F, V, S, O extends SearchPage<E>>
            extends AbstractSearchSelectProjectTask.Builder<B, T, E, K, D, F, V, S, O>
            implements PageSearchTask.Builder<B, T, E, K, D, F, V, S, O> {

        public static final int DEFAULT_LIMIT = 100;

        protected long offset;
        protected int limit = DEFAULT_LIMIT;

        public Builder(DataModelTypes<E, K, D, F, V, S> dataModelTypes) {
            super(dataModelTypes);
        }

        @Override
        public B withOffset(long offset) {
            if (offset < 0) {
                throw new IllegalArgumentException("Invalid offset!");
            }
            this.offset = offset;
            return Types.cast(this);
        }

        @Override
        public B withLimit(int limit) {
            if (limit < 1) {
                throw new IllegalArgumentException("Invalid limit!");
            }
            this.limit = limit;
            return Types.cast(this);
        }

        @Override
        protected void validate() {
            super.validate();
            if (offset < 0 || limit < 1) {
                throw new IllegalStateException();
            }
        }
    }
}
