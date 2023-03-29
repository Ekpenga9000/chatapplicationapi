package com.louiscodes.chatapplication.repository;

import com.louiscodes.chatapplication.entity.ContactsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactsRepository extends JpaRepository<ContactsEntity, Long> {
}
