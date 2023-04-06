package io.codeleaf.oerm.generic.tasks;

import io.codeleaf.oerm.Ordering;

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

        @SuppressWarnings("unchecked")
        B withOrder(Ordering<F>... ordering);

        B withOrder(List<Ordering<F>> ordering);

        @SuppressWarnings("unchecked")
        default B withAscendingOrder(F fieldName) {
            return withOrder(Ordering.createAsc(fieldName));
        }

        @SuppressWarnings("unchecked")
        default B withDescendingOrder(F fieldName) {
            return withOrder(Ordering.createDesc(fieldName));
        }
    }
}
