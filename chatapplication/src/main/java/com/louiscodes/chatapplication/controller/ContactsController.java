package com.louiscodes.chatapplication.controller;

import com.louiscodes.chatapplication.dto.AccountHolderDTO;
import com.louiscodes.chatapplication.dto.ContactsDTO;
import com.louiscodes.chatapplication.service.ContactsService;
import com.louiscodes.chatapplication.shared.customexceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contacts")
public class ContactsController {

    private ContactsService contactsService;

    @PostMapping("/{accountHolderId}/add-contact/{contactID}")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolderDTO addAccount(@PathVariable Long accountHolderId, @PathVariable Long contactId) throws BadRequestException {
        return contactsService.addContact(accountHolderId, contactId);
    }

    @GetMapping("/get-contacts/{accountHolderId}")
    public List<AccountHolderDTO> getAllContacts(@PathVariable Long accountHolderId){
        return contactsService.getAllContacts(accountHolderId);
    }

    @GetMapping("/{accountHolderId}/get-contact/{contactId}")
    public AccountHolderDTO getContactById(@PathVariable Long accountHolderId, @PathVariable Long contactId) throws BadRequestException {
        return contactsService.getContactById(accountHolderId, contactId);
    }

    @DeleteMapping("/{accountHolderId}/remove-contact/{contactId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContactById(@PathVariable Long accountHolderId, @PathVariable Long contactId){
        contactsService.removeContact(accountHolderId, contactId);
    }

}
