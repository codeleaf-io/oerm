package io.codeleaf.oerm.tasks.impl;

import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.selection.Selection;
import io.codeleaf.oerm.Ordering;
import io.codeleaf.oerm.SearchPage;
import io.codeleaf.oerm.tasks.PageSearchTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PageSearchTaskImpl<D, F, V, H> extends AbstractPageSearchTask<D, F, V, H, SearchPage<H>> {

    private PageSearchTaskImpl(long offset, int limit, List<F> projection, Class<H> searchHitType, Selection selection, Class<F> fieldNameType, Class<V> fieldValueType, D dataType, List<Ordering<F>> order) {
        super(offset, limit, projection, searchHitType, selection, fieldNameType, fieldValueType, dataType, Types.cast(SearchPage.class), order);
    }

    public static final class Builder<D, F, V, H> extends AbstractPageSearchTask.Builder<
            Builder<D, F, V, H>,
            PageSearchTaskImpl<D, F, V, H>,
            D, F, V, H, SearchPage<H>> {

        public Builder(D dataType, Class<F> fieldNameType, Class<V> fieldValueType, Class<H> searchHitType) {
            super(dataType, fieldNameType, fieldValueType, searchHitType);
        }

        @Override
        public PageSearchTaskImpl<D, F, V, H> build() {
            validate();
            return new PageSearchTaskImpl<>(
                    offset, limit, Collections.unmodifiableList(new ArrayList<>(projection)),
                    searchHitType, selection, fieldNameType, fieldValueType, dataType,
                    Collections.unmodifiableList(new ArrayList<>(order)));
        }
    }
}
