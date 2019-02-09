package io.codeleaf.oerm.mapper.entity;

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

    public EntityMeta(UUID uuid, String dataType, Set<IdentifierWithType> dataSubjects, IdentifierWithType legalOwner, IdentifierWithType dataSteward, List<IdentifierWithType> partition) {
        this.uuid = uuid;
        this.dataType = dataType;
        this.dataSubjects = dataSubjects;
        this.legalOwner = legalOwner;
        this.dataSteward = dataSteward;
        this.partition = partition;
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
}
