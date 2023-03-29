package com.louiscodes.chatapplication.repository;

import com.louiscodes.chatapplication.entity.MessagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessagesRepository extends JpaRepository<MessagesEntity, Long> {

    List<MessagesEntity> findBySenderIdAndReceiverIdOrderBySentAtAsc(Long senderId, Long receiverId);

    List<MessagesEntity> findByReceiverIdAndIsReadOrderBySentAtAsc(Long recieverId, Boolean isRead);

}
