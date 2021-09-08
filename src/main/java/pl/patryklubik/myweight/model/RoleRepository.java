package pl.patryklubik.myweight.model;

import java.util.Optional;

public interface RoleRepository {

    Optional<Role> findById(Integer id);
}
