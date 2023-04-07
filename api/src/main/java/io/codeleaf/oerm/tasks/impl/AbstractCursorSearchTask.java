package io.codeleaf.oerm.tasks.impl;

import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.Ordering;
import io.codeleaf.oerm.SearchCursor;
import io.codeleaf.oerm.tasks.CursorSearchTask;

import java.util.List;

public abstract class AbstractCursorSearchTask<D, F, V, H, O extends SearchCursor<H>> extends AbstractSearchSelectProjectTask<D, F, V, H, O> implements CursorSearchTask<D, F, V, H, O> {

    private final int bufferSize;

    protected AbstractCursorSearchTask(int bufferSize, List<F> projection, Class<H> searchHitType, Selection selection, Class<F> fieldNameType, Class<V> fieldValueType, D dataType, Class<O> searchCursorType, List<Ordering<F>> order) {
        super(searchHitType, selection, fieldNameType, fieldValueType, dataType, searchCursorType, projection, order);
        this.bufferSize = bufferSize;
    }

    @Override
    public int getBufferSize() {
        return bufferSize;
    }

    public static abstract class Builder<
            B extends Builder<B, T, D, F, V, H, O>,
            T extends AbstractCursorSearchTask<D, F, V, H, O>,
            D,
            F,
            V,
            H,
            O extends SearchCursor<H>
            > extends AbstractSearchSelectProjectTask.Builder<B, T, D, F, V, H, O>
            implements CursorSearchTask.Builder<B, T, D, F, V, H, O> {

        public static final int DEFAULT_BUFFER_SIZE = 100;

        protected int bufferSize = DEFAULT_BUFFER_SIZE;

        public Builder(D dataType, Class<F> fieldNameType, Class<V> fieldValueType, Class<H> searchHitType) {
            super(dataType, fieldNameType, fieldValueType, searchHitType);
        }

        @Override
        public B withBufferSize(int bufferSize) {
            if (bufferSize < 0) {
                throw new IllegalArgumentException("Invalid bufferSize!");
            }
            this.bufferSize = bufferSize;
            return Types.cast(this);
        }

        @Override
        protected void validate() {
            super.validate();
            if (bufferSize < 0) {
                throw new IllegalStateException();
            }
        }
    }
}
