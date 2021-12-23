package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

import com.example.demo.Entity.User;


public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findByUsername(String username);

}
