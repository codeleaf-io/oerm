package io.codeleaf.oerm.object.mapping;

import io.codeleaf.oerm.object.Entity;
import io.codeleaf.oerm.object.Reference;
import io.codeleaf.oerm.object.Team;
import io.codeleaf.oerm.object.Tenant;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class ObjectMeta implements Entity.Meta {

    private final java.util.UUID uuid;
    private final Class<? extends Entity> entityType;
    private final Set<Reference<Tenant>> dataSubjects;
    private final Reference<Tenant> legalOwner;
    private final Reference<Team> dataSteward;
    private final List<Reference<Tenant>> partition;

    public ObjectMeta(java.util.UUID uuid, Class<? extends Entity> entityType, Set<Reference<Tenant>> dataSubjects, Reference<Tenant> legalOwner, Reference<Team> dataSteward, List<Reference<Tenant>> partition) {
        this.uuid = uuid;
        this.entityType = entityType;
        this.dataSubjects = dataSubjects;
        this.legalOwner = legalOwner;
        this.dataSteward = dataSteward;
        this.partition = partition;
    }

    @Override
    public java.util.UUID getUUID() {
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

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.METHOD})
    public @interface UUID {
        Class<? extends Generator> generator() default Generator.RandomUUID.class;

        Class<? extends Mapper<?>> mapper() default Mapper.FromString.class;

        interface Mapper<T> {
            Class<T> getFieldType();

            java.util.UUID generate(Class<? extends Entity> entityType, Method fieldName, T fieldValue);

            class FromString implements Mapper<String> {
                @Override
                public Class<String> getFieldType() {
                    return String.class;
                }

                @Override
                public java.util.UUID generate(Class<? extends Entity> entityType, Method fieldName, String fieldValue) {
                    return java.util.UUID.fromString(fieldValue);
                }
            }
        }

        interface Generator {
            java.util.UUID generate(Class<? extends Entity> entityType, Map<Method, Object> fields);

            class RandomUUID implements Generator {
                @Override
                public java.util.UUID generate(Class<? extends Entity> entityType, Map<Method, Object> fields) {
                    return java.util.UUID.randomUUID();
                }
            }
        }
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface DataType {
        String value() default "";

        String version() default "";
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.METHOD})
    public @interface DataSubject {
        Class<? extends Tenant> entityType() default Tenant.class;

        String identifier() default "";
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.METHOD})
    public @interface LegalOwner {
        Class<? extends Tenant> entityType() default Tenant.class;

        String identifier() default "";
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.TYPE, ElementType.METHOD})
    public @interface DataSteward {
        Class<? extends Team> entityType() default Team.class;

        String identifier() default "";
    }

    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Partition {
    }
}
