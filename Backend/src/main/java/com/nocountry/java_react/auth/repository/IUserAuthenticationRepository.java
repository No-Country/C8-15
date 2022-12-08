package com.nocountry.java_react.auth.repository;

import com.nocountry.java_react.auth.model.UserAuthentication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserAuthenticationRepository extends JpaRepository<UserAuthentication, String> {
    UserAuthentication findUserByEmail(String email);

    boolean existsByEmail(String email);
}