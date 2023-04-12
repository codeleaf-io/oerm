package io.codeleaf.oerm.entity;

import io.codeleaf.modeling.data.IdentifierWithType;
import io.codeleaf.modeling.data.RecordWithType;

import java.net.URI;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public final class EntityRecord {

    private final RecordWithType record;
    private final Meta meta;

    private EntityRecord(RecordWithType record, Meta meta) {
        this.record = record;
        this.meta = meta;
    }

    public RecordWithType getRecord() {
        return record;
    }

    public Meta getMeta() {
        return meta;
    }

    public static EntityRecord create(RecordWithType record, Meta meta) {
        Objects.requireNonNull(record);
        Objects.requireNonNull(meta);
        return new EntityRecord(record, meta);
    }

    public interface Meta {

        UUID getUUID();

        String getDataType();

        Set<IdentifierWithType> getDataSubjects();

        IdentifierWithType getLegalOwner();

        IdentifierWithType getDataSteward();

        List<IdentifierWithType> getPartition();

        Source getSource();
    }

    public interface Source {

        String getSystemName();

        String getSystemId();

        String getLocalId();

        URI getURI();

        Long getCreationTime();

        Long getUpdationTime();

        Long getReadTime();

        Integer getVersion();
    }
}
