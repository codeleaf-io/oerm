package io.codeleaf.oerm.dal.impl.oem;

import io.codeleaf.oerm.object.Account;
import io.codeleaf.oerm.object.Entity;
import io.codeleaf.oerm.object.Reference;
import io.codeleaf.oerm.object.mapping.*;

/* This defines:
 * A. The Structure of the Object           (accomplished by the class definition itself)   => Class<? extends Entity>
 * B. The Structure of the Entity           (@Optional, @Required, @Ignored, @Mapping)      => EntitySchema
 * C. How to generate the ObjectMeta        (@ObjectMeta.*)                                 => Entity.ObjectMeta
 * D. How to map between an Object and an Entity (@Optional, @Required, @Generated)         => EntityRecord
 */

// To generate the EntitySchema                                                             [defineEntitySchema]
/* 1a. If we have a user defined object, translate it into object fields (Method-based)     [entityToMethodFields]
 * 1b. User defined object fields, translate to Method-based                                [fieldsToMethodFields]
 * 2. Validate all non-Generated Required Fields are set                                    [ensureNonGeneratedRequiredFieldsAreSet]
 * 3. Add APPLICATION Generated Fields (make sure non-Optional Generated fields are set)    [generateFieldValues]
 * 4. Generate the Object ObjectMeta                                                        [generateEntityMeta^todo]
 * 5. Translate the user defined object fields into entity fields                           [mapFieldsToEntity]
 * R1: Generate Entity from EntityRecord
 * R2: Generate Map<Supplier<?>, Object> from Map<String, ValueWithType>
 */
@ObjectMeta.DataSteward(identifier = "wetqweteqw")
@ObjectMeta.DataType(value = "Note", version = "0.1.0")
public interface Note extends Entity {

    @ObjectMeta.UUID
    @Generated(Locality.STORE)
    default String getIdentifier() {
        return java.util.UUID.randomUUID().toString();
    }

    @Optional
    @Mapping("subject")
    String getSubject();

    @Ignored
    String getMessage();

    @ObjectMeta.DataSubject
    @Required
    @Mapping("writer")
    Reference<Account> getCreator();
}
