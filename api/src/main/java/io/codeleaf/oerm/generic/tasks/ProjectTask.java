package io.codeleaf.oerm.generic.tasks;

import io.codeleaf.common.utils.Types;

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
