package com.example.lesson61.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lesson61.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
