package com.enosnery.desafiojava.repository;

import com.enosnery.desafiojava.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByUuid(UUID uuid);

    boolean existsUserByEmail(String email);

    User findByEmail(String email);

    boolean existsUserByEmailAndPassword(String email, String password);

    boolean existsByToken(String token);
}
