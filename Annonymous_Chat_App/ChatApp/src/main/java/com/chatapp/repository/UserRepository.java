package com.chatapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chatapp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsername(String username);

	
	@Query(value = "SELECT username, email FROM user_table",nativeQuery = true)
	public List<Object[]> getUsers();
	
	@Query(value = "SELECT username, email FROM user_table WHERE user_id = :userId", nativeQuery = true)
	Object getUserById(@Param("userId") int userId);

}
