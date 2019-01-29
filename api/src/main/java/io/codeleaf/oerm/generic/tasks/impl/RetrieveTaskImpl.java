package io.codeleaf.oerm.generic.tasks.impl;

import io.codeleaf.oerm.Ordering;
import io.codeleaf.oerm.generic.tasks.RetrieveTask;
import io.codeleaf.modeling.selection.Selection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class RetrieveTaskImpl<D, F, V, H> extends AbstractSearchSelectProjectTask<D, F, V, H, H> implements RetrieveTask<D, F, V, H> {

    private RetrieveTaskImpl(List<F> projection, Class<H> searchHitType, Selection selection, Class<F> fieldNameType, Class<V> fieldValueType, D dataType) {
        super(searchHitType, selection, fieldNameType, fieldValueType, dataType, searchHitType, projection, Collections.emptyList());
    }

    public static final class Builder<D, F, V, H> extends AbstractSearchSelectProjectTask.Builder<
            Builder<D, F, V, H>,
            RetrieveTaskImpl<D, F, V, H>,
            D, F, V, H, H> {

        public Builder() {
        }

        public Builder(D dataType, Class<F> fieldNameType, Class<V> fieldValueType, Class<H> searchHitType) {
            super(dataType, fieldNameType, fieldValueType, searchHitType);
        }

        @SuppressWarnings("unchecked")
        @Override
        public Builder<D, F, V, H> withOrder(Ordering<F>... ordering) {
            throw new IllegalStateException("Not allowed when building a RetrieveTask!");
        }

        @Override
        public RetrieveTaskImpl<D, F, V, H> build() {
            validate();
            return new RetrieveTaskImpl<>(
                    Collections.unmodifiableList(new ArrayList<>(projection)),
                    searchHitType, selection, fieldNameType, fieldValueType, dataType);
        }
    }
}
