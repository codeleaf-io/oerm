package io.codeleaf.dal;

import io.codeleaf.dal.types.Entity;

import java.util.Objects;

public final class Ordering<F> {

    private final F fieldName;
    private final Order order;

    private Ordering(F fieldName, Order order) {
        this.fieldName = fieldName;
        this.order = order;
    }

    public F getFieldName() {
        return fieldName;
    }

    public Order getOrder() {
        return order;
    }

    public enum Order {
        ASCENDING,
        DESCENDING
    }

    public static <F> Ordering<F> createAsc(F fieldName) {
        return create(fieldName, Order.ASCENDING);
    }

    public static <F> Ordering<F> createDesc(F fieldName) {
        return create(fieldName, Order.DESCENDING);
    }

    public static <F> Ordering<F> create(F fieldName, Order order) {
        Objects.requireNonNull(fieldName);
        Objects.requireNonNull(order);
        return new Ordering<>(fieldName, order);
    }
}
