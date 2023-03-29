package com.louiscodes.chatapplication.service;

import com.louiscodes.chatapplication.dto.AccountHolderDTO;
import com.louiscodes.chatapplication.dto.ContactsDTO;
import com.louiscodes.chatapplication.entity.AccountHolderEntity;
import com.louiscodes.chatapplication.entity.ContactsEntity;
import com.louiscodes.chatapplication.repository.AccountHolderRepository;
import com.louiscodes.chatapplication.shared.customexceptions.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ContactsService {

    @Autowired
    private final AccountHolderRepository accountHolderRepository;

    private final ModelMapper modelMapper;
    public ContactsService(AccountHolderRepository accountHolderRepository, ModelMapper modelMapper) {
        this.accountHolderRepository = accountHolderRepository;
        this.modelMapper = modelMapper;
    }


    public AccountHolderDTO addContact(Long accountHolderId, Long contactId) throws BadRequestException {
        Optional<AccountHolderEntity> accountHolderEntityOptional = accountHolderRepository.findById(accountHolderId);
        Optional<AccountHolderEntity> contactOptional = accountHolderRepository.findById(contactId);

        if(!accountHolderEntityOptional.isPresent() || !contactOptional.isPresent()){
            throw new ResourceNotFoundException("This contact could not be found");
        }

        AccountHolderEntity accountHolder = accountHolderEntityOptional.get();
        AccountHolderEntity contact = contactOptional.get();

        Set<ContactsEntity> contactsList = accountHolder.getContacts();

        for(ContactsEntity existingContact : contactsList){
            if(existingContact.getAccountHolder().getId().equals(contactId)){
                throw new BadRequestException("This contact already exists");
            }
        }

        ContactsEntity newContact = new ContactsEntity();
        newContact.setAccountHolder(accountHolder);

        contactsList.add(newContact);
        accountHolder.setContacts(contactsList);
        accountHolderRepository.save(accountHolder);

        return modelMapper.map(accountHolder, AccountHolderDTO.class);

    }


    public List<AccountHolderDTO> getAllContacts(Long accountHolderId){
        Optional<AccountHolderEntity> accountHolderEntityOptional = accountHolderRepository.findById(accountHolderId);

        if(!accountHolderEntityOptional.isPresent()){
            throw new ResourceNotFoundException("This account does not exist");
        }

        AccountHolderEntity accountHolder = accountHolderEntityOptional.get();

        return accountHolder.getContacts().stream()
                .map(contact -> modelMapper.map(contact, AccountHolderDTO.class))
                .collect(Collectors.toList());
    }

    public AccountHolderDTO getContactById(Long accountHolderId, Long contactId) throws BadRequestException{
        Optional<AccountHolderEntity> accountHolderOptional = accountHolderRepository.findById(accountHolderId);

        if(!accountHolderOptional.isPresent()){
            throw new ResourceNotFoundException("This account does not Exist");
        }

        AccountHolderEntity accountHolder = accountHolderOptional.get();

        Set<ContactsEntity> allContacts = accountHolder.getContacts();

        boolean isPresent = false;

        for(ContactsEntity contact : allContacts){
            if(contact.getId().equals(contactId)){
                isPresent = true;
            }
        }

        if(!isPresent){
            throw new BadRequestException("This contact does not exist");
        }

        Optional<AccountHolderEntity> contactOptional = accountHolderRepository.findById(contactId);

        if(!contactOptional.isPresent()){
            throw new ResourceNotFoundException("This contact does not exist");
        }

        AccountHolderEntity contact = contactOptional.get();

        return modelMapper.map(contact, AccountHolderDTO.class);

    }

    public AccountHolderDTO removeContact(Long accountHolderId, Long contactId){
        Optional<AccountHolderEntity> accountHolderEntityOptional = accountHolderRepository.findById(accountHolderId);

        if(!accountHolderEntityOptional.isPresent()){
            throw new ResourceNotFoundException("This contact is not found!");
        }

        Optional<AccountHolderEntity> contactOptional = accountHolderRepository.findById(contactId);

        if(!contactOptional.isPresent()){
            throw new ResourceNotFoundException("This contact is not found!");
        }

        AccountHolderEntity accountHolder = accountHolderEntityOptional.get();
        AccountHolderEntity contact = contactOptional.get();

        accountHolder.getContacts().remove(contact);
        contact.getContacts().remove(accountHolder);

        AccountHolderEntity savedAccountHolder = accountHolderRepository.save(accountHolder);

        return modelMapper.map(savedAccountHolder, AccountHolderDTO.class);
    }

}
