package org.binchoo.genshin.dailycheck.repos;

import org.binchoo.genshin.dailycheck.entities.LoginTokenOwner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginTokenOwnerRepository extends JpaRepository<LoginTokenOwner, Long> {
    Optional<LoginTokenOwner> findByName(String name);
}
