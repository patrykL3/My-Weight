package pl.patryklubik.myweight.model.security;

import java.util.Set;


public interface PermissionRepository {

    Set<Permission> findByRoles(Role role);
}
