package io.codeleaf.oerm.impl.tasks.data;

import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.Ordering;
import io.codeleaf.oerm.SearchCursor;
import io.codeleaf.oerm.impl.tasks.AbstractCursorSearchTask;
import io.codeleaf.oerm.tasks.data.CursorSearchTask;

import java.util.List;

public class GenericCursorSearchTask<E, K, D, F, V, S>
        extends AbstractCursorSearchTask<E, K, D, F, V, S, SearchCursor<E>>
        implements CursorSearchTask<E, K, D, F, V, S, SearchCursor<E>> {

    public GenericCursorSearchTask(DataModelTypes<E, K, D, F, V, S> dataModelTypes, D dataType, Selection selection, List<Ordering<F>> order, List<F> projection, int bufferSize) {
        super(dataModelTypes, dataType, selection, order, projection, bufferSize);
    }

    public static class Builder<E, K, D, F, V, S>
            extends AbstractCursorSearchTask.Builder
            <
                    Builder<E, K, D, F, V, S>,
                    GenericCursorSearchTask<E, K, D, F, V, S>,
                    E, K, D, F, V, S, SearchCursor<E>>
            implements CursorSearchTask.Builder
            <
                    Builder<E, K, D, F, V, S>,
                    GenericCursorSearchTask<E, K, D, F, V, S>,
                    E, K, D, F, V, S, SearchCursor<E>> {

        public Builder(DataModelTypes<E, K, D, F, V, S> dataModelTypes) {
            super(dataModelTypes);
        }

        @Override
        public GenericCursorSearchTask<E, K, D, F, V, S> build() {
            validate();
            return new GenericCursorSearchTask<>(dataModelTypes, dataType, selection,
                    List.copyOf(order), List.copyOf(projection), bufferSize);
        }
    }
}
