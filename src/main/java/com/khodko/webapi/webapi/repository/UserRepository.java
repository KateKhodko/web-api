package com.khodko.webapi.webapi.repository;

import com.khodko.webapi.webapi.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findByLogin(String login);

    boolean existsByLogin(String login);
}
