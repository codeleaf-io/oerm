package io.codeleaf.dal.types;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface Entity {

    interface Meta {

        UUID getUUID();

        Class<?> getEntityType();

        Set<Reference<Tenant>> getDataSubjects();

        Reference<Tenant> getLegalOwner();

        Reference<Team> getDataSteward();

        List<Reference<Tenant>> getPartition();

        AccessAuthorization getAccessAuhthorization();

    }

    String getIdentifier();

    Meta getMeta();

}
