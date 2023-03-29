package com.louiscodes.chatapplication.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
public class MessagesDTO {

    private Long message_id;

    @NotEmpty(message = "Message cannot be empty")
    private String message;

    private Long sender_id;

    private Long receiver_id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sentAt;

    private boolean isRead;

}

