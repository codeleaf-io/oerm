package io.codeleaf.oerm.tasks;

import java.util.Set;

public interface ListDataTypesTask<D> extends DataTypeTask<D, Set<D>> {

    interface Builder<
            B extends Builder<B, T, D>,
            T extends ListDataTypesTask<D>,
            D> extends DataTypeTask.Builder<B, T, D, Set<D>> {
    }

}
