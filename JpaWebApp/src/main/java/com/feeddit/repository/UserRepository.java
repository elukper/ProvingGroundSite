package com.feeddit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feeddit.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
