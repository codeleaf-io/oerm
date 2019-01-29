package io.codeleaf.oerm.entity;

import io.codeleaf.modeling.data.RecordTypeBuilder;

import java.util.HashMap;
import java.util.Map;

public final class EntitySchemaProvider {

    private final Map<String, EntitySchema> entitySchemas;

    private EntitySchemaProvider(Map<String, EntitySchema> entitySchemas) {
        this.entitySchemas = entitySchemas;
    }

    public boolean hasSchema(String dataType) {
        return entitySchemas.containsKey(dataType);
    }

    public EntitySchema getSchema(String dataType) {
        return entitySchemas.get(dataType);
    }

    public static EntitySchemaProvider create() {
        Map<String, EntitySchema> entitySchemas = new HashMap<>();
        EntitySchema entitySchema = createEntitySchema();
        entitySchemas.put(entitySchema.getDataType(), entitySchema);
        EntitySchema tenantSchema = createTenantSchema(entitySchema);
        entitySchemas.put(tenantSchema.getDataType(), tenantSchema);
        EntitySchema teamSchema = createTeamSchema(tenantSchema);
        entitySchemas.put(teamSchema.getDataType(), teamSchema);
        EntitySchema userSchema = createUserSchema(entitySchema);
        entitySchemas.put(userSchema.getDataType(), userSchema);
        EntitySchema accountSchema = createAccountSchema(entitySchema, userSchema.getDataType(), tenantSchema.getDataType());
        entitySchemas.put(accountSchema.getDataType(), accountSchema);
        return new EntitySchemaProvider(entitySchemas);
    }

    private static EntitySchema createEntitySchema() {
        String dataType = getDataType("Entity");
        return EntitySchema.create(dataType,
                RecordTypeBuilder.create()
                        .withRequiredField("id").identifier(dataType)
                        .endRecord());
    }

    private static EntitySchema createTenantSchema(EntitySchema entitySchema) {
        return EntitySchema.create(getDataType("Tenant"),
                RecordTypeBuilder.create()
                        .withRequiredField("name").text()
                        .endRecord(),
                entitySchema);
    }

    private static EntitySchema createTeamSchema(EntitySchema teamSchema) {
        return EntitySchema.create(getDataType("Team"),
                RecordTypeBuilder.create()
                        .endRecord(),
                teamSchema);
    }

    private static EntitySchema createUserSchema(EntitySchema entitySchema) {
        return EntitySchema.create(getDataType("User"),
                RecordTypeBuilder.create()
                        .endRecord(),
                entitySchema);
    }

    private static EntitySchema createAccountSchema(EntitySchema entitySchema, String userDataType, String tenantDataType) {
        return EntitySchema.create(getDataType("Account"),
                RecordTypeBuilder.create()
                        .withRequiredField("name").text()
                        .withRequiredField("related_user").identifier(userDataType)
                        .withRequiredField("related_tenant").identifier(tenantDataType)
                        .endRecord(),
                entitySchema);
    }

    private static String getDataType(String shortName) {
        return "io.codeleaf.dal.entities." + shortName + ":0.1.0";
    }
}
