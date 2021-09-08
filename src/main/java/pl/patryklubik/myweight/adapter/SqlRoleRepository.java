package pl.patryklubik.myweight.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.patryklubik.myweight.model.Role;
import pl.patryklubik.myweight.model.RoleRepository;

@Repository
interface SqlRoleRepository extends RoleRepository, JpaRepository<Role, Integer> {
}
