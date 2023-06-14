package com.espritnoir.backend.repositories;

import com.espritnoir.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(@Param("email") String email);
}
