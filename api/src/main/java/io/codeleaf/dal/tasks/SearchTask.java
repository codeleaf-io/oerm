package io.codeleaf.dal.tasks;

import io.codeleaf.dal.Ordering;

import java.util.List;

public interface SearchTask<D, F, H, O> extends ReadTask<D, O> {

    Class<H> getSearchHitType();

    Class<F> getFieldNameType();

    List<Ordering<F>> getOrder();

    interface Builder<
            B extends Builder<B, T, D, F, H, O>,
            T extends SearchTask<D, F, H, O>,
            D,
            F,
            H,
            O>
            extends ReadTask.Builder<B, T, D, O> {

        B withSearchHitType(Class<H> searchHitType);

        B withFieldNameType(Class<F> fieldNameType);

        B withOrder(Ordering<F>... ordering);

        default B withAscendingOrder(F fieldName) {
            return withOrder(Ordering.createAsc(fieldName));
        }

        default B withDescendingOrder(F fieldName) {
            return withOrder(Ordering.createDesc(fieldName));
        }
    }
}
