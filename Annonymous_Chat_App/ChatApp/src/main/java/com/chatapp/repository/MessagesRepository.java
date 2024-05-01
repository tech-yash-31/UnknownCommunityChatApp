package com.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chatapp.dto.MessagesDTO;
import com.chatapp.entity.Messages;

@Repository
public interface MessagesRepository extends JpaRepository<Messages, Integer> {

	
}
