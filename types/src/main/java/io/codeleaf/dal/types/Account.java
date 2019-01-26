package io.codeleaf.dal.types;

public interface Account extends Entity {

    String getName();

    Reference<User> getRelatedUser();

    Reference<Tenant> getRelatedTenant();

}
