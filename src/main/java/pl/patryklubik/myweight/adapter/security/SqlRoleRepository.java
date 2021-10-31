package pl.patryklubik.myweight.adapter.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.patryklubik.myweight.model.security.Role;
import pl.patryklubik.myweight.model.security.RoleRepository;

@Repository
interface SqlRoleRepository extends RoleRepository, JpaRepository<Role, Integer> {
}
