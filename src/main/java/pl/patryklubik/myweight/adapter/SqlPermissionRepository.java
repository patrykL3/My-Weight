package pl.patryklubik.myweight.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.patryklubik.myweight.model.Permission;
import pl.patryklubik.myweight.model.PermissionRepository;


@Repository
interface SqlPermissionRepository extends PermissionRepository, JpaRepository<Permission, Integer> {
}
