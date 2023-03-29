package com.louiscodes.chatapplication.controller;

import com.louiscodes.chatapplication.dto.AccountHolderDTO;
import com.louiscodes.chatapplication.entity.AccountHolderEntity;
import com.louiscodes.chatapplication.repository.AccountHolderRepository;
import com.louiscodes.chatapplication.service.AccountHolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account-holder")
public class AccountHolderController {

    @Autowired
    private AccountHolderService accountHolderService;

    @PostMapping("/createaccount")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolderDTO createAccount(@RequestBody AccountHolderDTO accountHolderDTO){
        return accountHolderService.createAccountHolder(accountHolderDTO);
    }

    @GetMapping("/retrieve/{id}")
    public AccountHolderDTO getAccountHolderById(@PathVariable Long id){
        return accountHolderService.getAccountHolderById(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AccountHolderDTO> updateAccountHolderById(@PathVariable Long id, @RequestBody AccountHolderDTO accountHolderDTO){
        AccountHolderDTO updatedAccount = accountHolderService.updateAccountHolder(id, accountHolderDTO);

        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAccountHolderById(@PathVariable Long id){
        accountHolderService.deleteAccountHolderById(id);
    }
}
