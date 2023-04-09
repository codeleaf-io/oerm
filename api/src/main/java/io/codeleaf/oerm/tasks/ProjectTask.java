package io.codeleaf.oerm.tasks;

import io.codeleaf.common.utils.Types;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public interface ProjectTask<E, K, D, F, V, S, O> extends DatabaseTask<E, K, D, F, V, S, O> {

    List<F> getProjection();

    interface Builder<
            B extends Builder<B, T, E, K, D, F, V, S, O>,
            T extends ProjectTask<E, K, D, F, V, S, O>,
            E, K, D, F, V, S, O>
            extends ReadTask.Builder<B, T, E, K, D, F, V, S, O> {

        @SuppressWarnings("unchecked")
        default B withProjections(F... projection) {
            Objects.requireNonNull(projection);
            return withProjections(Arrays.asList(projection));
        }

        default B withProjections(Collection<F> projection) {
            Objects.requireNonNull(projection);
            for (F fieldName : projection) {
                withProjection(fieldName);
            }
            return Types.cast(this);
        }

        B withProjection(F fieldName);
    }
}
