package com.example.springilmiofotoalbum.service;

import com.example.springilmiofotoalbum.model.Message;
import com.example.springilmiofotoalbum.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message createNewMessage(Message message){
        Message newMessage = new Message();
        newMessage.setName(message.getName());
        newMessage.setSurname(message.getSurname());
        newMessage.setEmail(message.getEmail());
        newMessage.setMessage(message.getMessage());


        return messageRepository.save(newMessage);
    }
}
