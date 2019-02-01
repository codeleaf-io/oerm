package io.codeleaf.oerm.dal.impl.oem;

import io.codeleaf.common.utils.Types;
import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.oerm.dal.impl.DataTypeMapper;
import io.codeleaf.oerm.entity.EntityRecord;
import io.codeleaf.oerm.object.Entity;
import io.codeleaf.oerm.object.Reference;
import io.codeleaf.oerm.object.Tenant;

import java.util.*;

public final class MetaFactory {

    private final DataTypeMapper dataTypeMapper;

    public MetaFactory(DataTypeMapper dataTypeMapper) {
        this.dataTypeMapper = dataTypeMapper;
    }

    public Entity.Meta createObjectMeta(EntityRecord.Meta entityMeta) {
        return new ObjectMetaImpl(
                entityMeta.getUUID(),
                dataTypeMapper.map(entityMeta.getDataType()),
                mapFromEntity(entityMeta.getDataSubjects()),
                mapFromEntity(entityMeta.getLegalOwner()),
                mapFromEntity(entityMeta.getDataSteward()),
                mapFromEntity(entityMeta.getPartition()));
    }

    private <T extends Entity> Reference<T> mapFromEntity(IdentifierWithType identifierWithType) {
        Class<?> entityType = dataTypeMapper.map(identifierWithType.getType().getDataType());
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

    public EntityRecord.Meta createEntityMeta(Entity.Meta objectMeta) {
        Objects.requireNonNull(objectMeta);
        return new EntityMetaImpl(
                objectMeta.getUUID(),
                dataTypeMapper.map(objectMeta.getEntityType()),
                mapFromObject(objectMeta.getDataSubjects()),
                mapFromObject(objectMeta.getLegalOwner()),
                mapFromObject(objectMeta.getDataSteward()),
                mapFromObject(objectMeta.getPartition()));
    }

    private IdentifierWithType mapFromObject(Reference<? extends Tenant> reference) {
        String identifier = reference.getIdentifier();
        String dataType = dataTypeMapper.map(reference.getEntityType());
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
