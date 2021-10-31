package pl.patryklubik.myweight.model.security;

import java.util.Optional;

public interface RoleRepository {

    Optional<Role> findById(Integer id);
}
