package com.example.Project_2_Chat_Room.controller;

import com.example.Project_2_Chat_Room.modeles.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MessageController {
    @MessageMapping("/message")
    @SendTo("/topic/return-to")
    public Message message(@RequestBody Message message){
        try {
            Thread.sleep(1000);
        }catch (Exception e){
            log.error("e: ", e);
        }
        return message;
    }
}
