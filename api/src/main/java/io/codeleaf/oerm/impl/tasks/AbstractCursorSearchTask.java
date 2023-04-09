package io.codeleaf.oerm.impl.tasks;

import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.Ordering;
import io.codeleaf.oerm.SearchCursor;
import io.codeleaf.oerm.tasks.data.CursorSearchTask;

import java.util.List;

public abstract class AbstractCursorSearchTask<E, K, D, F, V, S, O extends SearchCursor<E>>
        extends AbstractSearchSelectProjectTask<E, K, D, F, V, S, O>
        implements CursorSearchTask<E, K, D, F, V, S, O> {

    private final int bufferSize;

    protected AbstractCursorSearchTask(DataModelTypes<E, K, D, F, V, S> dataModelTypes, D dataType, Selection selection, List<Ordering<F>> order, List<F> projection, int bufferSize) {
        super(dataModelTypes, Types.cast(SearchCursor.class), dataType, selection, order, projection);
        this.bufferSize = bufferSize;
    }

    @Override
    public int getBufferSize() {
        return bufferSize;
    }

    public static abstract class Builder<
            B extends Builder<B, T, E, K, D, F, V, S, O>,
            T extends AbstractCursorSearchTask<E, K, D, F, V, S, O>,
            E, K, D, F, V, S, O extends SearchCursor<E>>
            extends AbstractSearchSelectProjectTask.Builder<B, T, E, K, D, F, V, S, O>
            implements CursorSearchTask.Builder<B, T, E, K, D, F, V, S, O> {

        public static final int DEFAULT_BUFFER_SIZE = 100;

        protected int bufferSize = DEFAULT_BUFFER_SIZE;

        public Builder(DataModelTypes<E, K, D, F, V, S> dataModelTypes) {
            super(dataModelTypes);
        }

        @Override
        public B withBufferSize(int bufferSize) {
            if (bufferSize < 1) {
                throw new IllegalArgumentException("Invalid bufferSize!");
            }
            this.bufferSize = bufferSize;
            return Types.cast(this);
        }

        @Override
        protected void validate() {
            super.validate();
            if (bufferSize < 1) {
                throw new IllegalStateException();
            }
        }
    }
}
