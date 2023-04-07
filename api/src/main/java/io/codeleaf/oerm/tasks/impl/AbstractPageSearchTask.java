package io.codeleaf.oerm.tasks.impl;

import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.Ordering;
import io.codeleaf.oerm.SearchPage;
import io.codeleaf.oerm.tasks.PageSearchTask;

import java.util.List;

public abstract class AbstractPageSearchTask<D, F, V, H, O extends SearchPage<H>> extends AbstractSearchSelectProjectTask<D, F, V, H, O> implements PageSearchTask<D, F, V, H, O> {

    private final long offset;
    private final int limit;

    protected AbstractPageSearchTask(long offset, int limit, List<F> projection, Class<H> searchHitType, Selection selection, Class<F> fieldNameType, Class<V> fieldValueType, D dataType, Class<O> searchPageType, List<Ordering<F>> order) {
        super(searchHitType, selection, fieldNameType, fieldValueType, dataType, searchPageType, projection, order);
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
            B extends Builder<B, T, D, F, V, H, O>,
            T extends AbstractPageSearchTask<D, F, V, H, O>,
            D,
            F,
            V,
            H,
            O extends SearchPage<H>
            > extends AbstractSearchSelectProjectTask.Builder<B, T, D, F, V, H, O>
            implements PageSearchTask.Builder<B, T, D, F, V, H, O> {

        public static final int DEFAULT_LIMIT = 100;

        protected long offset;
        protected int limit = DEFAULT_LIMIT;

        public Builder(D dataType, Class<F> fieldNameType, Class<V> fieldValueType, Class<H> searchHitType) {
            super(dataType, fieldNameType, fieldValueType, searchHitType);
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
