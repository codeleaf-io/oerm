package io.codeleaf.oerm.entity.impl;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.oerm.entity.EntityRecord;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public final class EntityMeta implements EntityRecord.Meta {

    private final UUID uuid;
    private final String dataType;
    private final Set<IdentifierWithType> dataSubjects;
    private final IdentifierWithType legalOwner;
    private final IdentifierWithType dataSteward;
    private final List<IdentifierWithType> partition;
    private final EntityRecord.Source source;

    public EntityMeta(UUID uuid, String dataType, Set<IdentifierWithType> dataSubjects, IdentifierWithType legalOwner, IdentifierWithType dataSteward, List<IdentifierWithType> partition, EntityRecord.Source source) {
        this.uuid = uuid;
        this.dataType = dataType;
        this.dataSubjects = dataSubjects;
        this.legalOwner = legalOwner;
        this.dataSteward = dataSteward;
        this.partition = partition;
        this.source = source;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public String getDataType() {
        return dataType;
    }

    @Override
    public Set<IdentifierWithType> getDataSubjects() {
        return dataSubjects;
    }

    @Override
    public IdentifierWithType getLegalOwner() {
        return legalOwner;
    }

    @Override
    public IdentifierWithType getDataSteward() {
        return dataSteward;
    }

    @Override
    public List<IdentifierWithType> getPartition() {
        return partition;
    }

    @Override
    public EntityRecord.Source getSource() {
        return source;
    }
}
