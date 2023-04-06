package io.codeleaf.oerm.generic.tasks;

public interface DetermineDataTypeTask<E, D> extends DataTypeTask<D, D> {

    E getEntity();

    Class<E> getEntityType();

    interface Builder<
            B extends Builder<B, T, E, D>,
            T extends DetermineDataTypeTask<E, D>,
            E,
            D> extends DataTypeTask.Builder<B, T, D, D> {

        B withEntity(E entity);
    }

}
