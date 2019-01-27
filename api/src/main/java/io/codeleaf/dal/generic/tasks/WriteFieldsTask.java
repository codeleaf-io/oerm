package io.codeleaf.dal.generic.tasks;

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

        B withFieldNameType(Class<F> fieldNameType);

        B withFieldValueType(Class<V> fieldValueType);

        @SuppressWarnings("unchecked")
        default B withFields(Map<F, V> fields) {
            Objects.requireNonNull(fields);
            for (Map.Entry<F, V> entry : fields.entrySet()) {
                withField(entry.getKey(), entry.getValue());
            }
            return (B) this;
        }

        B withField(F fieldName, V fieldValue);
    }
}
