package io.codeleaf.dal.generic.tasks.impl;

import io.codeleaf.common.utils.Types;
import io.codeleaf.dal.Ordering;
import io.codeleaf.dal.SearchPageAndCount;
import io.codeleaf.dal.generic.tasks.PageSearchAndCountTask;
import io.codeleaf.modeling.selection.Selection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class PageSearchAndCountTaskImpl<D, F, V, H> extends AbstractPageSearchTask<D, F, V, H, SearchPageAndCount<H>>
        implements PageSearchAndCountTask<D, F, V, H, SearchPageAndCount<H>> {

    private PageSearchAndCountTaskImpl(long offset, int limit, List<F> projection, Class<H> searchHitType, Selection selection, Class<F> fieldNameType, Class<V> fieldValueType, D dataType, List<Ordering<F>> order) {
        super(offset, limit, projection, searchHitType, selection, fieldNameType, fieldValueType, dataType, Types.cast(SearchPageAndCount.class), order);
    }

    public static final class Builder<D, F, V, H> extends AbstractPageSearchTask.Builder<
            Builder<D, F, V, H>,
            PageSearchAndCountTaskImpl<D, F, V, H>,
            D, F, V, H, SearchPageAndCount<H>> {

        public Builder() {
        }

        public Builder(D dataType, Class<F> fieldNameType, Class<V> fieldValueType, Class<H> searchHitType) {
            super(dataType, fieldNameType, fieldValueType, searchHitType);
        }

        @Override
        public PageSearchAndCountTaskImpl<D, F, V, H> build() {
            validate();
            return new PageSearchAndCountTaskImpl<>(
                    offset, limit, Collections.unmodifiableList(new ArrayList<>(projection)),
                    searchHitType, selection, fieldNameType, fieldValueType, dataType,
                    Collections.unmodifiableList(new ArrayList<>(order)));
        }
    }
}
