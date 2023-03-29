package com.louiscodes.chatapplication.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ContactsDTO {
    private Long id;

    private Long accountHolderId;

    private Set<Long> contactIds = new HashSet<>();

    public ContactsDTO() {}

    public ContactsDTO(Long id, Long accountHolderId, Set<Long> contactIds) {
        this.id = id;
        this.accountHolderId = accountHolderId;
        this.contactIds = contactIds;
    }
}
