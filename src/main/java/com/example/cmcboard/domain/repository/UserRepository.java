package com.example.cmcboard.domain.repository;

import com.example.cmcboard.domain.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
