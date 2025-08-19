package com.example.demo.Controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController
{
    @MessageMapping("/comment")
    @SendTo("/topic/comments")
    public String comment(String comment){
        return comment;
    }

    @MessageMapping("/typing")
    @SendTo("topic/typing")
    public String typing(String username){
        return String.format("{} is typing ...", username);
    }
}
