package io.codeleaf.oerm.mapper.object;

import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.oerm.entity.EntityRecord;
import io.codeleaf.oerm.mapper.entity.EntityMeta;
import io.codeleaf.oerm.object.Entity;
import io.codeleaf.oerm.object.Reference;
import io.codeleaf.oerm.object.Tenant;
import io.codeleaf.oerm.object.mapping.ObjectMeta;

import java.util.*;

public final class ObjectMetaMapper {

    public Entity.Meta mapEntityMeta(EntityRecord.Meta entityMeta) {
        Objects.requireNonNull(entityMeta);
        return new ObjectMeta(
                entityMeta.getUUID(),
                EntityTypes.determineEntityType(entityMeta.getDataType()),
                mapFromEntity(entityMeta.getDataSubjects()),
                mapFromEntity(entityMeta.getLegalOwner()),
                mapFromEntity(entityMeta.getDataSteward()),
                mapFromEntity(entityMeta.getPartition()));
    }

    private <T extends Entity> Reference<T> mapFromEntity(IdentifierWithType identifierWithType) {
        Class<?> entityType = EntityTypes.determineEntityType(identifierWithType.getType().getDataType());
        if (!Tenant.class.isAssignableFrom(entityType)) {
            throw new IllegalArgumentException();
        }
        String identifier = identifierWithType.getValue();
        return Reference.create(identifier, Types.cast(entityType));
    }

    private Set<Reference<Tenant>> mapFromEntity(Set<IdentifierWithType> dataSubjects) {
        Set<Reference<Tenant>> tenants = new LinkedHashSet<>();
        for (IdentifierWithType dataSubject : dataSubjects) {
            tenants.add(mapFromEntity(dataSubject));
        }
        return tenants;
    }

    private List<Reference<Tenant>> mapFromEntity(List<IdentifierWithType> partition) {
        List<Reference<Tenant>> tenants = new ArrayList<>(partition.size());
        for (IdentifierWithType part : partition) {
            tenants.add(mapFromEntity(part));
        }
        return tenants;
    }

    public EntityRecord.Meta mapObjectMeta(Entity.Meta objectMeta) {
        Objects.requireNonNull(objectMeta);
        return new EntityMeta(
                objectMeta.getUUID(),
                EntityTypes.determineDataType(objectMeta.getEntityType()),
                mapFromObject(objectMeta.getDataSubjects()),
                mapFromObject(objectMeta.getLegalOwner()),
                mapFromObject(objectMeta.getDataSteward()),
                mapFromObject(objectMeta.getPartition()));
    }

    private IdentifierWithType mapFromObject(Reference<? extends Tenant> reference) {
        String identifier = reference.getIdentifier();
        String dataType = EntityTypes.determineDataType(reference.getEntityType());
        return IdentifierWithType.create(identifier, dataType);
    }

    private Set<IdentifierWithType> mapFromObject(Set<Reference<Tenant>> references) {
        Set<IdentifierWithType> tenants = new LinkedHashSet<>();
        for (Reference<Tenant> reference : references) {
            tenants.add(mapFromObject(reference));
        }
        return tenants;
    }

    private List<IdentifierWithType> mapFromObject(List<Reference<Tenant>> references) {
        List<IdentifierWithType> tenants = new ArrayList<>(references.size());
        for (Reference<Tenant> reference : references) {
            tenants.add(mapFromObject(reference));
        }
        return tenants;
    }
}
