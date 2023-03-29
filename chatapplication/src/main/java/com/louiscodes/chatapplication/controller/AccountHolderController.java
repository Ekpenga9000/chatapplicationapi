package com.louiscodes.chatapplication.controller;

import com.louiscodes.chatapplication.entity.AccountHolderEntity;
import com.louiscodes.chatapplication.repository.AccountHolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountHolderController {

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @PostMapping("/createaccounts")
    public void createAccount(@RequestBody AccountHolderEntity accountHolderEntity){
        accountHolderRepository.save(accountHolderEntity);
    }

}
