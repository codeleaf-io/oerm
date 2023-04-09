package io.codeleaf.oerm.impl.tasks.data;

import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.Ordering;
import io.codeleaf.oerm.SearchCursorAndCount;
import io.codeleaf.oerm.impl.tasks.AbstractCursorSearchTask;
import io.codeleaf.oerm.tasks.data.CursorSearchAndCountTask;

import java.util.List;

public class GenericCursorSearchAndCountTask<E, K, D, F, V, S>
        extends AbstractCursorSearchTask<E, K, D, F, V, S, SearchCursorAndCount<E>>
        implements CursorSearchAndCountTask<E, K, D, F, V, S, SearchCursorAndCount<E>> {

    public GenericCursorSearchAndCountTask(DataModelTypes<E, K, D, F, V, S> dataModelTypes, D dataType, Selection selection, List<Ordering<F>> order, List<F> projection, int bufferSize) {
        super(dataModelTypes, dataType, selection, order, projection, bufferSize);
    }

    public static class Builder<E, K, D, F, V, S>
            extends AbstractCursorSearchTask.Builder
            <
                    Builder<E, K, D, F, V, S>,
                    GenericCursorSearchAndCountTask<E, K, D, F, V, S>,
                    E, K, D, F, V, S, SearchCursorAndCount<E>>
            implements CursorSearchAndCountTask.Builder
            <
                    Builder<E, K, D, F, V, S>,
                    GenericCursorSearchAndCountTask<E, K, D, F, V, S>,
                    E, K, D, F, V, S, SearchCursorAndCount<E>> {

        public Builder(DataModelTypes<E, K, D, F, V, S> dataModelTypes) {
            super(dataModelTypes);
        }

        @Override
        public GenericCursorSearchAndCountTask<E, K, D, F, V, S> build() {
            validate();
            return new GenericCursorSearchAndCountTask<>(dataModelTypes, dataType, selection,
                    List.copyOf(order), List.copyOf(projection), bufferSize);
        }
    }
}
