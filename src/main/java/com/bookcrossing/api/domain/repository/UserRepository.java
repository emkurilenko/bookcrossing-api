package com.bookcrossing.api.domain.repository;

import com.bookcrossing.api.domain.entity.User;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseCrudRepository<User, Long> {

    boolean existsByLogin(String login);

    boolean existsByEmail(String email);

}
