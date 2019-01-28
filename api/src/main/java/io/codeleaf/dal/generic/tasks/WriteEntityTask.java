package io.codeleaf.dal.generic.tasks;

public interface WriteEntityTask<E, D, O> extends WriteTask<D, O> {

    Class<E> getEntityType();

    E getEntity();

    interface Builder<
            B extends Builder<B, T, E, D, O>,
            T extends WriteEntityTask<E, D, O>,
            E,
            D,
            O>
            extends WriteTask.Builder<B, T, D, O> {

        B withEntityType(Class<E> entityType);

        B withEntity(E entity);
    }
}
