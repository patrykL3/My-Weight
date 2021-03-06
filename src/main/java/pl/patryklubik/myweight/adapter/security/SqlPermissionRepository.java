package pl.patryklubik.myweight.adapter.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.patryklubik.myweight.model.security.Permission;
import pl.patryklubik.myweight.model.security.PermissionRepository;


@Repository
interface SqlPermissionRepository extends PermissionRepository, JpaRepository<Permission, Integer> {
}
