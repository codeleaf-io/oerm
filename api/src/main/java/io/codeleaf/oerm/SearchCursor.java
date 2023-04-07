package io.codeleaf.oerm;

import java.io.IOException;
import java.util.Iterator;

public interface SearchCursor<H> extends Iterator<H>, AutoCloseable {

    @Override
    void close() throws IOException;

}
