package pl.patryklubik.myweight.adapter.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.patryklubik.myweight.model.security.User;
import pl.patryklubik.myweight.model.security.UserRepository;

@Repository
interface SqlUserRepository extends UserRepository, JpaRepository<User, Integer> {
}
