package com.louiscodes.chatapplication.controller;

import com.louiscodes.chatapplication.dto.MessagesDTO;
import com.louiscodes.chatapplication.entity.MessagesEntity;
import com.louiscodes.chatapplication.service.MessagesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
public class MessagesController {

    private MessagesService messagesService;

    @PostMapping("/create-message")
    public MessagesDTO createMessage(@RequestBody MessagesDTO messagesDTO){
        return messagesService.createMessage(messagesDTO);
    }

    @GetMapping("/sender/{senderId}/receiver/{receiverId}")
    public List<MessagesDTO> getMessagesBySenderAndReceiver(@PathVariable Long senderId, @PathVariable Long receiverId){
        return messagesService.getAllMessagesBySendAndReceiver(senderId, receiverId);
    }

    @GetMapping("/{receiverId}/unread-messages")
    public List<MessagesDTO> getUnreadMessagesByReceiver(@PathVariable Long receiverId){
        return messagesService.getUnreadMessagesByReceiver(receiverId);
    }

    @GetMapping("/{messageId}")
    public MessagesDTO getMessageById(@PathVariable Long messageId){
        return messagesService.getMessagesById(messageId);
    }

    @PostMapping("/mark-as-read")
    public ResponseEntity<String> markMessagesAsRead(@RequestBody List<Long> messageIds){
        messagesService.markMessagesAsRead(messageIds);

        return ResponseEntity.ok("Message marked as read");
    }

    @DeleteMapping("/delete-message/{messageId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMessageById(@PathVariable Long messageId){
        messagesService.deleteMessageById(messageId);
    }
}
