package io.codeleaf.oerm.tasks;

public interface RetrieveTask<D, F, V, H>
        extends
        SelectTask<D, F, V, H>,
        ProjectTask<D, F, H>,
        SearchTask<D, F, H, H> {

    interface Builder<
            B extends Builder<B, T, D, F, V, H>,
            T extends RetrieveTask<D, F, V, H>,
            D,
            F,
            V,
            H>
            extends
            SelectTask.Builder<B, T, D, F, V, H>,
            ProjectTask.Builder<B, T, D, F, H>,
            SearchTask.Builder<B, T, D, F, H, H> {
    }
}
