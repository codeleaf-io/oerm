package io.codeleaf.dal.generic.tasks.impl;

import io.codeleaf.common.utils.Types;
import io.codeleaf.dal.Ordering;
import io.codeleaf.dal.SearchCursorAndCount;
import io.codeleaf.modeling.selection.Selection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CursorSearchAndCountTaskImpl<D, F, V, H> extends AbstractCursorSearchTask<D, F, V, H, SearchCursorAndCount<H>> {

    private CursorSearchAndCountTaskImpl(int bufferSize, List<F> projection, Class<H> searchHitType, Selection selection, Class<F> fieldNameType, Class<V> fieldValueType, D dataType, List<Ordering<F>> order) {
        super(bufferSize, projection, searchHitType, selection, fieldNameType, fieldValueType, dataType, Types.cast(SearchCursorAndCount.class), order);
    }

    public static final class Builder<D, F, V, H> extends AbstractCursorSearchTask.Builder<
            Builder<D, F, V, H>,
            CursorSearchAndCountTaskImpl<D, F, V, H>,
            D, F, V, H, SearchCursorAndCount<H>> {

        public Builder() {
        }

        public Builder(D dataType, Class<F> fieldNameType, Class<V> fieldValueType, Class<H> searchHitType) {
            super(dataType, fieldNameType, fieldValueType, searchHitType);
        }

        @Override
        public CursorSearchAndCountTaskImpl<D, F, V, H> build() {
            validate();
            return new CursorSearchAndCountTaskImpl<>(
                    bufferSize, Collections.unmodifiableList(new ArrayList<>(projection)),
                    searchHitType, selection, fieldNameType, fieldValueType, dataType,
                    Collections.unmodifiableList(new ArrayList<>(order)));
        }
    }
}
