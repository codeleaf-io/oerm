package io.codeleaf.dal.tasks;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public interface ProjectTask<D, F, O> extends ReadTask<D, O> {

    List<F> getProjection();

    Class<F> getFieldNameType();

    interface Builder<
            B extends Builder<B, T, D, F, O>,
            T extends ProjectTask<D, F, O>,
            D,
            F,
            O>
            extends ReadTask.Builder<B, T, D, O> {

        @SuppressWarnings("unchecked")
        default B withProjections(F... projection) {
            Objects.requireNonNull(projection);
            return withProjections(Arrays.asList(projection));
        }

        @SuppressWarnings("unchecked")
        default B withProjections(Collection<F> projection) {
            Objects.requireNonNull(projection);
            for (F fieldName : projection) {
                withProjection(fieldName);
            }
            return (B) this;
        }

        B withProjection(F fieldName);
    }
}
