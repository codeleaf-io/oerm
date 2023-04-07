package io.codeleaf.oerm.tasks.impl;

import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.Ordering;
import io.codeleaf.oerm.SearchCursorAndCount;
import io.codeleaf.oerm.tasks.CursorSearchAndCountTask;

import java.util.List;

public final class CursorSearchAndCountTaskImpl<D, F, V, H> extends AbstractCursorSearchTask<D, F, V, H, SearchCursorAndCount<H>>
        implements CursorSearchAndCountTask<D, F, V, H, SearchCursorAndCount<H>> {

    private CursorSearchAndCountTaskImpl(int bufferSize, List<F> projection, Class<H> searchHitType, Selection selection, Class<F> fieldNameType, Class<V> fieldValueType, D dataType, List<Ordering<F>> order) {
        super(bufferSize, projection, searchHitType, selection, fieldNameType, fieldValueType, dataType, Types.cast(SearchCursorAndCount.class), order);
    }

    public static final class Builder<D, F, V, H> extends AbstractCursorSearchTask.Builder<
            Builder<D, F, V, H>,
            CursorSearchAndCountTaskImpl<D, F, V, H>,
            D, F, V, H, SearchCursorAndCount<H>>
            implements CursorSearchAndCountTask.Builder<Builder<D, F, V, H>, CursorSearchAndCountTaskImpl<D, F, V, H>, D, F, V, H, SearchCursorAndCount<H>> {

        public Builder(D dataType, Class<F> fieldNameType, Class<V> fieldValueType, Class<H> searchHitType) {
            super(dataType, fieldNameType, fieldValueType, searchHitType);
        }

        @Override
        public CursorSearchAndCountTaskImpl<D, F, V, H> build() {
            validate();
            return new CursorSearchAndCountTaskImpl<>(
                    bufferSize, List.copyOf(projection),
                    searchHitType, selection, fieldNameType, fieldValueType, dataType,
                    List.copyOf(order));
        }
    }
}
