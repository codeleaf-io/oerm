package io.codeleaf.oerm.object;

public interface Account extends Entity {

    String getName();

    Reference<User> getRelatedUser();

    Reference<Tenant> getRelatedTenant();

}
