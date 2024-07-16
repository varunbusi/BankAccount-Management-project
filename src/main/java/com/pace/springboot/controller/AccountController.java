package com.pace.springboot.controller;

import com.pace.springboot.entity.Account;
import com.pace.springboot.repository.AccountRepository;
import com.pace.springboot.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService bankAccountService;

    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return bankAccountService.createAccount(account);
    }

    @GetMapping("/{id}")
    public Optional<Account> getAccount(@PathVariable Long id) {
        return bankAccountService.getAccount(id);
    }

    @PostMapping("/{id}/deposit")
    public Account deposit(@PathVariable Long id, @RequestParam Double amount) {
        return bankAccountService.deposit(id, amount);
    }

    @PostMapping("/{id}/withdraw")
    public Account withdraw(@PathVariable Long id, @RequestParam Double amount) {
        return bankAccountService.withdraw(id, amount);
    }
    

        private AccountRepository accountRepository;

        public AccountController(AccountRepository accountRepository) {
            this.accountRepository = accountRepository;
        }

        @GetMapping
        public List<Account> getAllAccounts() {
            return accountRepository.findAll(); // Assuming you have a JpaRepository for BankAccount
        }
}