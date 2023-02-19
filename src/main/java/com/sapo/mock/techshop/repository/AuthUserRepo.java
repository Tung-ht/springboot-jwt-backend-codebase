package com.sapo.mock.techshop.repository;

import com.sapo.mock.techshop.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthUserRepo extends JpaRepository<AuthUser, Integer> {
    AuthUser findAuthUserByUsername(String username);
}
