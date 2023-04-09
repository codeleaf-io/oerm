package io.codeleaf.oerm.object;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface Entity {

    interface Meta {

        UUID getUUID();

        Class<? extends Entity> getEntityType();

        Set<Reference<Tenant>> getDataSubjects();

        Reference<Tenant> getLegalOwner();

        Reference<Team> getDataSteward();

        List<Reference<Tenant>> getPartition();
    }

    String getIdentifier();

    Meta getMeta();
}
