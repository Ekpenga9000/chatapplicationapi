package com.louiscodes.chatapplication.repository;

import com.louiscodes.chatapplication.entity.MessagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessagesRepository extends JpaRepository<MessagesEntity, Long> {

    List<MessagesEntity> findBySender_IdAndReceiver_IdOrderBySentAtAsc(Long sender_id, Long receiver_id);

    List<MessagesEntity> findByReceiverIdAndIsReadOrderBySentAtAsc(Long receiver_id, Boolean isRead);

    List<MessagesEntity> findBySenderId(Long senderId);

}
