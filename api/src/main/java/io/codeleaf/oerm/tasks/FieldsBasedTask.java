package io.codeleaf.oerm.tasks;

import io.codeleaf.common.utils.Types;

import java.util.Map;
import java.util.Objects;

public interface FieldsBasedTask<E, K, D, F, V, S, O> extends DatabaseTask<E, K, D, F, V, S, O> {

    Map<F, V> getFields();

    interface Builder<
            B extends Builder<B, T, E, K, D, F, V, S, O>,
            T extends FieldsBasedTask<E, K, D, F, V, S, O>,
            E, K, D, F, V, S, O>
            extends DatabaseTask.Builder<B, T, E, K, D, F, V, S, O> {

        default B withFields(Map<F, V> fields) {
            Objects.requireNonNull(fields);
            for (Map.Entry<F, V> entry : fields.entrySet()) {
                withField(entry.getKey(), entry.getValue());
            }
            return Types.cast(this);
        }

        B withField(F fieldName, V fieldValue);
    }
}
