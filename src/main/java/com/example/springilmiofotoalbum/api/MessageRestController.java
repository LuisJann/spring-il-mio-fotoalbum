package com.example.springilmiofotoalbum.api;

import com.example.springilmiofotoalbum.model.Message;
import com.example.springilmiofotoalbum.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/messages")
public class MessageRestController {
    @Autowired
    MessageService messageService;

    @PostMapping
    public Message createMessage(@Valid @RequestBody Message message, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return messageService.createNewMessage(message);
    }
}
