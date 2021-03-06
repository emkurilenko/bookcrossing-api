package com.bookcrossing.api.domain.repository;

import com.bookcrossing.api.domain.entity.User;

import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseCrudRepository<User, Long> {

    boolean existsByLogin(String login);

    boolean existsByEmail(String email);

    Optional<User> findByLogin(String login);

}
