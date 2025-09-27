package uz.brb.web_socket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import uz.brb.web_socket.dto.WebSocketChatMessage;

@Controller
public class ChatController {
    // Client /app/sendMessage ga yuborsa, server /topic/messages ga chiqaradi
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/javainuse")
    public WebSocketChatMessage sendMessage(@Payload WebSocketChatMessage message) {
        System.out.println("Backend oldi: " + message.getContent());
        return message;
    }

    @MessageMapping("/chat.newUser")
    @SendTo("/topic/javainuse")
    public WebSocketChatMessage addUser(@Payload WebSocketChatMessage webSocketChatMessage,
                                        SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", webSocketChatMessage.getSender());
        return webSocketChatMessage;
    }
}