package com.louiscodes.chatapplication.service;

import com.louiscodes.chatapplication.dto.AccountHolderDTO;
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
public class AccountHolderService {

    //CRUD operations for Account holder

    //Account holder can register
    @Autowired
    private final AccountHolderRepository accountHolderRepository;

    private final ModelMapper modelMapper;

    public AccountHolderService(AccountHolderRepository accountHolderRepository, ModelMapper modelMapper) {
        this.accountHolderRepository = accountHolderRepository;
        this.modelMapper = modelMapper;
    }

    //Create account
    public AccountHolderDTO createAccountHolder(AccountHolderDTO accountHolderDTO){
        AccountHolderEntity accountHolder = modelMapper.map(accountHolderDTO, AccountHolderEntity.class);
        AccountHolderEntity savedAccountHolder = accountHolderRepository.save(accountHolder);

        return modelMapper.map(savedAccountHolder, AccountHolderDTO.class);
    }

    //Get all Accounts
    public List<AccountHolderDTO> getAllAccountHolders(){
        List<AccountHolderEntity> accountHolders = accountHolderRepository.findAll();

        return accountHolders.stream()
                .map(accountHolder -> modelMapper.map(accountHolder, AccountHolderDTO.class))
                .collect(Collectors.toList());
    }

    //Get Account holder by ID
    public AccountHolderDTO getAccountHolderById(Long id){
        Optional<AccountHolderEntity> optionalAccountHolder = accountHolderRepository.findById(id);

        if(optionalAccountHolder.isPresent()){
            AccountHolderEntity accountHolder = optionalAccountHolder.get();
            return modelMapper.map(accountHolder, AccountHolderDTO.class);
        }else{
            throw new ResourceNotFoundException("This account could not be found");
        }
    }

    //Update Account holder
    public AccountHolderDTO updateAccountHolder(Long id, AccountHolderDTO accountHolderDTO){
        //check if there's an Account holder
        Optional<AccountHolderEntity> optionalAccountHolder = accountHolderRepository.findById(id);

        if(optionalAccountHolder.isPresent()){
            AccountHolderEntity accountHolder = optionalAccountHolder.get();
            modelMapper.map(accountHolderDTO, accountHolder);
            AccountHolderEntity savedAccountHolder = accountHolderRepository.save(accountHolder);

            return modelMapper.map(savedAccountHolder, AccountHolderDTO.class);
        }else{
            throw new ResourceNotFoundException("Account could not be found");
        }
    }

    public void deleteAccountHolderById(Long id){
        if(!accountHolderRepository.existsById(id)){
            throw new ResourceNotFoundException("Account holder does not exist");
        }

        accountHolderRepository.deleteById(id);
    }

}
