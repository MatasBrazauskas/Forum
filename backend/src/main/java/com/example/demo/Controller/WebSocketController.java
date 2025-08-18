package com.example.demo.Controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController
{
    @MessageMapping("/comment")
    @SendTo("topics/comments")
    public String sendMessage(String message)
    {
        return message;
    }
}
