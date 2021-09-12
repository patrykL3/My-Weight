package pl.patryklubik.myweight.model;

import java.util.Set;


public interface PermissionRepository {

    Set<Permission> findByRoles(Role role);
}
