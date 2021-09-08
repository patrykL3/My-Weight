package pl.patryklubik.myweight.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.patryklubik.myweight.model.User;
import pl.patryklubik.myweight.model.UserRepository;

@Repository
interface SqlUserRepository extends UserRepository, JpaRepository<User, Integer> {
}
