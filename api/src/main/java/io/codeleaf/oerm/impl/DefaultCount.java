package io.codeleaf.oerm.impl;

import io.codeleaf.oerm.Count;

public class DefaultCount implements Count {

    private final long count;

    public DefaultCount(long count) {
        this.count = count;
    }

    @Override
    public long getCount() {
        return count;
    }
}
