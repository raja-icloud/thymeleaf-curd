package com.example.thymeleaf_curd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.thymeleaf_curd.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
