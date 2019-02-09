package io.codeleaf.oerm.object.mapping;

import io.codeleaf.oerm.object.Account;
import io.codeleaf.oerm.object.Entity;
import io.codeleaf.oerm.object.Reference;

/* This defines:
 * A. The Structure of the Object           (accomplished by the class definition itself)   => Class<? extends Entity>
 * B. The Structure of the Entity           (@Optional, @Required, @Ignored, @Mapping)      => EntitySchema
 * C. How to generate the Meta              (@ObjectMeta.*)                                 => EntityRecord.Meta
 * D. How to map between Object and Entity  (@Mapping, @Generated)                          => EntityRecord
 */
@ObjectMeta.DataSteward(identifier = "wetqweteqw")
public interface Note extends Entity {

    @ObjectMeta.UUID
    @Generated
    default String getIdentifier() {
        return java.util.UUID.randomUUID().toString();
    }

    @Optional
    String getSubject();

    String getMessage();

    @ObjectMeta.Partition
    Reference<Account> getCreator();
}
