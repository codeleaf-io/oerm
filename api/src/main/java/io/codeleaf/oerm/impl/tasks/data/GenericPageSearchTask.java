package io.codeleaf.oerm.impl.tasks.data;

import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.Ordering;
import io.codeleaf.oerm.SearchPage;
import io.codeleaf.oerm.impl.tasks.AbstractPageSearchTask;
import io.codeleaf.oerm.tasks.data.PageSearchTask;

import java.util.List;

public class GenericPageSearchTask<E, K, D, F, V, S>
        extends AbstractPageSearchTask<E, K, D, F, V, S, SearchPage<E>>
        implements PageSearchTask<E, K, D, F, V, S, SearchPage<E>> {

    public GenericPageSearchTask(DataModelTypes<E, K, D, F, V, S> dataModelTypes, D dataType, Selection selection, List<Ordering<F>> order, List<F> projection, long offset, int limit) {
        super(dataModelTypes, dataType, selection, order, projection, offset, limit);
    }

    public static class Builder<E, K, D, F, V, S>
            extends AbstractPageSearchTask.Builder
            <
                    Builder<E, K, D, F, V, S>,
                    GenericPageSearchTask<E, K, D, F, V, S>,
                    E, K, D, F, V, S, SearchPage<E>>
            implements PageSearchTask.Builder
            <
                    Builder<E, K, D, F, V, S>,
                    GenericPageSearchTask<E, K, D, F, V, S>,
                    E, K, D, F, V, S, SearchPage<E>> {

        public Builder(DataModelTypes<E, K, D, F, V, S> dataModelTypes) {
            super(dataModelTypes);
        }

        @Override
        public GenericPageSearchTask<E, K, D, F, V, S> build() {
            validate();
            return new GenericPageSearchTask<>(dataModelTypes, dataType, selection, List.copyOf(order), List.copyOf(projection), offset, limit);
        }
    }
}
