package com.example.springilmiofotoalbum.repository;

import com.example.springilmiofotoalbum.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {

}
