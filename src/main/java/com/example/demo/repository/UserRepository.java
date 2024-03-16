package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
	@Query("SELECT U FROM Users U WHERE U.username = :username")
	public Users findUserByName(@Param("username") String username);

	@Query("SELECT U FROM Users U WHERE U.username = :username AND U.password = :password")
	public Users findUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
	
}
