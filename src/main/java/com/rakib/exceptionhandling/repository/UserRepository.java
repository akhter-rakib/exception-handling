package com.rakib.exceptionhandling.repository;

import com.rakib.exceptionhandling.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserId(int userId);
}
