package io.codeleaf.oerm.dal.impl.oem;

import io.codeleaf.oerm.object.Entity;
import io.codeleaf.oerm.object.Reference;
import io.codeleaf.oerm.object.Team;
import io.codeleaf.oerm.object.Tenant;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public final class ObjectMetaImpl implements Entity.Meta {

    private final UUID uuid;
    private final Class<? extends Entity> entityType;
    private final Set<Reference<Tenant>> dataSubjects;
    private final Reference<Tenant> legalOwner;
    private final Reference<Team> dataSteward;
    private final List<Reference<Tenant>> partition;

    public ObjectMetaImpl(UUID uuid, Class<? extends Entity> entityType, Set<Reference<Tenant>> dataSubjects, Reference<Tenant> legalOwner, Reference<Team> dataSteward, List<Reference<Tenant>> partition) {
        this.uuid = uuid;
        this.entityType = entityType;
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
    public Class<? extends Entity> getEntityType() {
        return entityType;
    }

    @Override
    public Set<Reference<Tenant>> getDataSubjects() {
        return dataSubjects;
    }

    @Override
    public Reference<Tenant> getLegalOwner() {
        return legalOwner;
    }

    @Override
    public Reference<Team> getDataSteward() {
        return dataSteward;
    }

    @Override
    public List<Reference<Tenant>> getPartition() {
        return partition;
    }
}
