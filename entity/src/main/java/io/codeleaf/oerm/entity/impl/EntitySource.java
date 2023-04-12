package io.codeleaf.oerm.entity.impl;

import io.codeleaf.oerm.entity.EntityRecord;

import java.net.URI;

public final class EntitySource implements EntityRecord.Source {

    private final String systemName;
    private final String systemId;
    private final String localId;
    private final URI uri;
    private final Long creationTime;
    private final Long updationTime;
    private final Long readTime;
    private final Integer version;

    public EntitySource(String systemName, String systemId, String localId, URI uri, Long creationTime, Long updationTime, Long readTime, Integer version) {
        this.systemName = systemName;
        this.systemId = systemId;
        this.localId = localId;
        this.uri = uri;
        this.creationTime = creationTime;
        this.updationTime = updationTime;
        this.readTime = readTime;
        this.version = version;
    }

    @Override
    public String getSystemName() {
        return systemName;
    }

    @Override
    public String getSystemId() {
        return systemId;
    }

    @Override
    public String getLocalId() {
        return localId;
    }

    @Override
    public URI getURI() {
        return uri;
    }

    @Override
    public Long getCreationTime() {
        return creationTime;
    }

    @Override
    public Long getUpdationTime() {
        return updationTime;
    }

    @Override
    public Long getReadTime() {
        return readTime;
    }

    @Override
    public Integer getVersion() {
        return version;
    }
}
