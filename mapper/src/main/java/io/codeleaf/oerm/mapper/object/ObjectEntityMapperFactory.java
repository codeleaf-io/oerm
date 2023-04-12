package io.codeleaf.oerm.mapper.object;

import io.codeleaf.common.utils.Methods;
import io.codeleaf.oerm.entity.EntitySchema;
import io.codeleaf.oerm.mapper.entity.EntitySchemaProvider;
import io.codeleaf.oerm.object.Entity;
import io.codeleaf.oerm.object.mapping.Optional;
import io.codeleaf.oerm.object.mapping.*;

import java.lang.reflect.Method;
import java.util.*;

public final class ObjectEntityMapperFactory {

    private final EntitySchemaProvider entitySchemaProvider;
    private final ObjectMetaParserProvider objectMetaParserProvider;
    private final ObjectFieldMapperProvider objectFieldMapperProvider;
    private final ObjectFieldGeneratorProvider objectFieldGeneratorProvider;
    private final ObjectMetaMapper objectMetaMapper;

    public ObjectEntityMapperFactory(EntitySchemaProvider entitySchemaProvider, ObjectMetaParserProvider objectMetaParserProvider, ObjectFieldMapperProvider objectFieldMapperProvider, ObjectFieldGeneratorProvider objectFieldGeneratorProvider, ObjectMetaMapper objectMetaMapper) {
        this.entitySchemaProvider = entitySchemaProvider;
        this.objectMetaParserProvider = objectMetaParserProvider;
        this.objectFieldMapperProvider = objectFieldMapperProvider;
        this.objectFieldGeneratorProvider = objectFieldGeneratorProvider;
        this.objectMetaMapper = objectMetaMapper;
    }

    public <E extends Entity> ObjectEntityMapper<E> createObjectEntityMapper(Class<E> entityType) {
        String dataType = EntityTypes.determineDataType(entityType);
        EntitySchema entitySchema = entitySchemaProvider.getSchema(dataType);
        Method[] nonIgnoredSuppliers = extractNonIgnoredSuppliers(entityType);
        Map<Method, ObjectFieldGenerator> applicationGenerators = extractApplicationGenerators(entityType);
        Map<Method, String> fieldNameMapping = extractFieldNames(nonIgnoredSuppliers);
        return new ObjectEntityMapper<>(
                entityType,
                dataType,
                nonIgnoredSuppliers,
                extractNonGeneratedRequiredSuppliers(entityType),
                applicationGenerators,
                getOptionalApplicationGenerators(applicationGenerators),
                fieldNameMapping,
                createReversedFieldNameMapping(fieldNameMapping),
                extractFieldMappers(nonIgnoredSuppliers),
                objectMetaParserProvider.getObjectMetaParser(entityType),
                objectMetaMapper,
                entitySchema);
    }

    private Map<String, Method> createReversedFieldNameMapping(Map<Method, String> fieldNameMapping) {
        Map<String, Method> reverseFieldNameMapping = new HashMap<>();
        for (Map.Entry<Method, String> entry : fieldNameMapping.entrySet()) {
            reverseFieldNameMapping.put(entry.getValue(), entry.getKey());
        }
        return reverseFieldNameMapping;
    }

    private Map<Method, String> extractFieldNames(Method[] suppliers) {
        Map<Method, String> names = new HashMap<>();
        for (Method method : suppliers) {
            Mapping mapping = method.getAnnotation(Mapping.class);
            if (mapping == null || mapping.value().isEmpty()) {
                names.put(method, method.getName());
            } else {
                names.put(method, method.getName());
            }
        }
        return names;
    }

    private Map<Method, ObjectFieldMapper> extractFieldMappers(Method[] suppliers) {
        Map<Method, ObjectFieldMapper> mappers = new HashMap<>();
        for (Method method : suppliers) {
            mappers.put(method, objectFieldMapperProvider.get(method.getAnnotation(Mapping.class)));
        }
        return mappers;
    }

    private Set<Method> getOptionalApplicationGenerators(Map<Method, ObjectFieldGenerator> applicationGenerators) {
        Set<Method> suppliers = new HashSet<>();
        for (Method method : applicationGenerators.keySet()) {
            if (Methods.hasAnnotation(method, Optional.class)) {
                suppliers.add(method);
            }
        }
        return suppliers;
    }

    private Method[] extractNonIgnoredSuppliers(Class<? extends Entity> entityType) {
        List<Method> suppliers = new ArrayList<>();
        for (Method method : entityType.getMethods()) {
            if (Methods.isSupplier(method) && !Methods.hasAnnotation(method, Ignored.class)) {
                suppliers.add(method);
            }
        }
        return suppliers.toArray(new Method[0]);
    }

    private Method[] extractNonGeneratedRequiredSuppliers(Class<? extends Entity> entityType) {
        List<Method> suppliers = new ArrayList<>();
        for (Method method : entityType.getMethods()) {
            if (Methods.isSupplier(method) && !Methods.hasAnnotation(method, Generated.class) && !Methods.hasAnnotation(method, Optional.class)) {
                suppliers.add(method);
            }
        }
        return suppliers.toArray(new Method[0]);
    }

    private Map<Method, ObjectFieldGenerator> extractApplicationGenerators(Class<? extends Entity> entityType) {
        Map<Method, ObjectFieldGenerator> suppliers = new LinkedHashMap<>();
        for (Method method : entityType.getMethods()) {
            Generated generated = method.getAnnotation(Generated.class);
            if (!(generated == null
                    || generated.value().equals(InStoreGenerated.class)
                    || generated.value().equals(Generated.NotSpecified.class) && !method.isDefault())) {
                ObjectFieldGenerator generator = objectFieldGeneratorProvider.get(generated.value());
                if (!generator.supportsType(method.getReturnType())) {
                    throw new IllegalStateException("Generator on field " + method.getName() + " does not support type: " + method.getReturnType().getCanonicalName());
                }
                suppliers.put(method, generator);
            }
        }
        return suppliers;
    }
}
