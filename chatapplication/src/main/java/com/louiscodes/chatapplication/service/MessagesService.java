package com.louiscodes.chatapplication.service;

import com.louiscodes.chatapplication.dto.MessagesDTO;
import com.louiscodes.chatapplication.entity.MessagesEntity;
import com.louiscodes.chatapplication.repository.MessagesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessagesService {

    @Autowired
    private final MessagesRepository messagesRepository;

    private final ModelMapper modelMapper;

    public MessagesService(MessagesRepository messagesRepository, ModelMapper modelMapper) {
        this.messagesRepository = messagesRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public MessagesDTO createMessage(MessagesDTO messagesDTO){
        try{
            MessagesEntity newMessage = modelMapper.map(messagesDTO, MessagesEntity.class);

            newMessage.setSentAt(LocalDateTime.now());
            MessagesEntity savedMessage = messagesRepository.save(newMessage);

            return modelMapper.map(savedMessage, MessagesDTO.class);
        }catch (Exception e){
            throw new RuntimeException("Failed to create message: " + e.getMessage(), e);
        }
    }

    public List<MessagesDTO> getAllMessagesBySendAndReceiver(Long sender_id, Long receiver_id){

        List<MessagesEntity> allMessages = messagesRepository.findBySender_IdAndReceiver_IdOrderBySentAtAsc(sender_id, receiver_id);

        return allMessages.stream()
                .map(message -> modelMapper.map(message, MessagesDTO.class))
                .collect(Collectors.toList());
    }

    public List<MessagesDTO> getUnreadMessagesByReceiver (Long receiverId){
        List<MessagesEntity> unreadMessages = messagesRepository.findByReceiverIdAndIsReadOrderBySentAtAsc(receiverId, false);

        return unreadMessages.stream()
                .map(unreadMessage -> modelMapper.map(unreadMessage, MessagesDTO.class))
                .collect(Collectors.toList());
    }

    public MessagesDTO getMessagesById(Long messageId){
        Optional<MessagesEntity> theMessageOptional = messagesRepository.findById(messageId);

        if(!theMessageOptional.isPresent()){
            throw new ResourceNotFoundException("This message does not exist");
        }

        MessagesEntity theMessage = theMessageOptional.get();

        return modelMapper.map(theMessage, MessagesDTO.class);
    }

    public void markMessagesAsRead(List<Long> messageIds){
        List<MessagesEntity> unreadMessages = messagesRepository.findAllById(messageIds);

        unreadMessages.forEach(unreadMessage -> unreadMessage.setRead(true));
        messagesRepository.saveAll(unreadMessages);
    }

    public void deleteMessageById(Long messageId){
        Optional<MessagesEntity> messageOptional = messagesRepository.findById(messageId);

        if(!messageOptional.isPresent()){
            throw new ResourceNotFoundException("This message does not exist");
        }

        MessagesEntity message = messageOptional.get();
        messagesRepository.delete(message);
    }
}
