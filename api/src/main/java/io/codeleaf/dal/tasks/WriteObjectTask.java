package io.codeleaf.dal.tasks;

public interface WriteObjectTask<E, D, O> extends WriteTask<D, O> {

    Class<E> getObjectType();

    E getObject();

    interface Builder<
            B extends Builder<B, T, E, D, O>,
            T extends WriteObjectTask<E, D, O>,
            E,
            D,
            O>
            extends WriteTask.Builder<B, T, D, O> {

        B withObjectType(Class<E> objectType);

        B withObject(E object);
    }
}
