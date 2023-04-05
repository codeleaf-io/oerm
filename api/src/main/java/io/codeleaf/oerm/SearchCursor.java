package io.codeleaf.oerm;

import io.codeleaf.modeling.task.TaskHandlingException;
import io.codeleaf.oerm.generic.RepositoryException;

import java.io.IOException;
import java.util.Iterator;

public interface SearchCursor<H> extends Iterator<H>, AutoCloseable {

    @Override
    void close() throws IOException;

}
