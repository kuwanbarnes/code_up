package com.codeup.codeup_demo.repo;

import com.codeup.codeup_demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
