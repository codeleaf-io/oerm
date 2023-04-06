package io.codeleaf.oerm.generic.tasks;

import io.codeleaf.common.utils.Types;

import java.util.Map;
import java.util.Objects;

public interface WriteFieldsTask<D, F, V, O> extends WriteTask<D, O> {

    Class<F> getFieldNameType();

    Class<V> getFieldValueType();

    Map<F, V> getFields();

    interface Builder<
            B extends Builder<B, T, D, F, V, O>,
            T extends WriteFieldsTask<D, F, V, O>,
            D,
            F,
            V,
            O>
            extends WriteTask.Builder<B, T, D, O> {

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
