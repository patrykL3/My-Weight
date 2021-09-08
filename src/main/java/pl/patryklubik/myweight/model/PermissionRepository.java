package pl.patryklubik.myweight.model;

import java.util.List;

public interface PermissionRepository {

    List<Permission> findByRole(Role role);
}
