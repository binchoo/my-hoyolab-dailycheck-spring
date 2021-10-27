package org.binchoo.genshin.dailycheck.user.repos;

import org.binchoo.genshin.dailycheck.user.entities.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginUserRepository extends JpaRepository<LoginUser, Long> {
    Optional<LoginUser> findByName(String name);
}
