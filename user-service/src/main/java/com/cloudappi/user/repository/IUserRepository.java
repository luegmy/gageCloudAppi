package com.cloudappi.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cloudappi.user.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer>{

}
