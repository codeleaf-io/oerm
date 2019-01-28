package io.codeleaf.dal.types.object;

public interface Account extends Entity {

    String getName();

    Reference<User> getRelatedUser();

    Reference<Tenant> getRelatedTenant();

}
