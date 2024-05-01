package com.chatapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chatapp.entity.Groups;

@Repository
public interface GroupsRepository extends JpaRepository<Groups, Integer> {

	@Query(value = "SELECT group_name FROM groups_table", nativeQuery = true)
	public List<Object[]> getGroupsinfo();
	
	Optional<Groups> findByGroupName(String groupName);
}
