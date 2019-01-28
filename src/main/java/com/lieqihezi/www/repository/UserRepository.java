package com.lieqihezi.www.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lieqihezi.www.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByName(String name);
}