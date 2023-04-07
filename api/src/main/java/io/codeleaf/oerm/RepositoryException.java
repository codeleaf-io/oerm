package io.codeleaf.oerm;

public class RepositoryException extends RuntimeException {

    private final Repository<?, ?, ?, ?, ?, ?> repository;

    public RepositoryException(Repository<?, ?, ?, ?, ?, ?> repository) {
        this.repository = repository;
    }

    public RepositoryException(Repository<?, ?, ?, ?, ?, ?> repository, String message) {
        super(message);
        this.repository = repository;
    }

    public RepositoryException(Repository<?, ?, ?, ?, ?, ?> repository, Throwable cause) {
        super(cause);
        this.repository = repository;
    }

    public RepositoryException(Repository<?, ?, ?, ?, ?, ?> repository, String message, Throwable cause) {
        super(message, cause);
        this.repository = repository;
    }

    public Repository<?, ?, ?, ?, ?, ?> getRepository() {
        return repository;
    }
}

