package io.codeleaf.dal.tasks.impl;

import io.codeleaf.dal.SearchCursor;
import io.codeleaf.dal.Ordering;
import io.codeleaf.modeling.selection.Selection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CursorSearchTaskImpl<D, F, V, H> extends AbstractCursorSearchTask<D, F, V, H, SearchCursor<H>> {

    @SuppressWarnings("unchecked")
    private CursorSearchTaskImpl(int bufferSize, List<F> projection, Class<H> searchHitType, Selection selection, Class<F> fieldNameType, Class<V> fieldValueType, D dataType, List<Ordering<F>> order) {
        super(bufferSize, projection, searchHitType, selection, fieldNameType, fieldValueType, dataType, (Class) SearchCursor.class, order);
    }

    public static final class Builder<D, F, V, H> extends AbstractCursorSearchTask.Builder<
            Builder<D, F, V, H>,
            CursorSearchTaskImpl<D, F, V, H>,
            D, F, V, H, SearchCursor<H>> {

        public Builder() {
        }

        public Builder(D dataType, Class<F> fieldNameType, Class<V> fieldValueType, Class<H> searchHitType) {
            super(dataType, fieldNameType, fieldValueType, searchHitType);
        }

        @Override
        public CursorSearchTaskImpl<D, F, V, H> build() {
            validate();
            return new CursorSearchTaskImpl<>(
                    bufferSize, Collections.unmodifiableList(new ArrayList<>(projection)),
                    searchHitType, selection, fieldNameType, fieldValueType, dataType,
                    Collections.unmodifiableList(new ArrayList<>(order)));
        }
    }
}
