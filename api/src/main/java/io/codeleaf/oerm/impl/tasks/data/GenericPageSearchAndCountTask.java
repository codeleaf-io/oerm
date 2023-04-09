package io.codeleaf.oerm.impl.tasks.data;

import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.DataModelTypes;
import io.codeleaf.oerm.Ordering;
import io.codeleaf.oerm.SearchPageAndCount;
import io.codeleaf.oerm.impl.tasks.AbstractPageSearchTask;
import io.codeleaf.oerm.tasks.data.PageSearchAndCountTask;

import java.util.List;

public class GenericPageSearchAndCountTask<E, K, D, F, V, S>
        extends AbstractPageSearchTask<E, K, D, F, V, S, SearchPageAndCount<E>>
        implements PageSearchAndCountTask<E, K, D, F, V, S, SearchPageAndCount<E>> {

    public GenericPageSearchAndCountTask(DataModelTypes<E, K, D, F, V, S> dataModelTypes, D dataType, Selection selection, List<Ordering<F>> order, List<F> projection, long offset, int limit) {
        super(dataModelTypes, dataType, selection, order, projection, offset, limit);
    }

    public static class Builder<E, K, D, F, V, S>
            extends AbstractPageSearchTask.Builder
            <
                    Builder<E, K, D, F, V, S>,
                    GenericPageSearchAndCountTask<E, K, D, F, V, S>,
                    E, K, D, F, V, S, SearchPageAndCount<E>>
            implements PageSearchAndCountTask.Builder
            <
                    Builder<E, K, D, F, V, S>,
                    GenericPageSearchAndCountTask<E, K, D, F, V, S>,
                    E, K, D, F, V, S, SearchPageAndCount<E>> {

        public Builder(DataModelTypes<E, K, D, F, V, S> dataModelTypes) {
            super(dataModelTypes);
        }

        @Override
        public GenericPageSearchAndCountTask<E, K, D, F, V, S> build() {
            validate();
            return new GenericPageSearchAndCountTask<>(dataModelTypes, dataType, selection, List.copyOf(order), List.copyOf(projection), offset, limit);
        }
    }
}
