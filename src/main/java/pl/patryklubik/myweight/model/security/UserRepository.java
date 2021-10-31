package pl.patryklubik.myweight.model.security;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);
    User getByUsername(String username);
    User save(User entity);
    boolean existsByUsername(String username);
}
