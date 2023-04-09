package io.codeleaf.oerm.tasks;

import io.codeleaf.oerm.Ordering;

import java.util.Arrays;
import java.util.List;

public interface ListTask<E, K, D, F, V, S, O> extends ReadTask<E, K, D, F, V, S, O> {

    List<Ordering<F>> getOrder();

    interface Builder<
            B extends Builder<B, T, E, K, D, F, V, S, O>,
            T extends ListTask<E, K, D, F, V, S, O>,
            E, K, D, F, V, S, O>
            extends ReadTask.Builder<B, T, E, K, D, F, V, S, O> {

        B withOrder(List<Ordering<F>> ordering);

        @SuppressWarnings("unchecked")
        default B withOrder(Ordering<F>... ordering) {
            return withOrder(ordering == null ? List.of() : Arrays.asList(ordering));
        }

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
