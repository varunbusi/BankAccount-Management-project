package com.pace.springboot.service;

import com.pace.springboot.entity.Account;
import com.pace.springboot.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository bankAccountRepository;

    public Account createAccount(Account account) {
        account.setBalance(0.0);
        return bankAccountRepository.save(account);
    }

    public Optional<Account> getAccount(Long id) {
        return bankAccountRepository.findById(id);
    }

    public Account deposit(Long id, Double amount) {
        Account account = bankAccountRepository.findById(id).orElseThrow();
        account.setBalance(account.getBalance() + amount);
        return bankAccountRepository.save(account);
    }

    public Account withdraw(Long id, Double amount) {
        Account account = bankAccountRepository.findById(id).orElseThrow();
        if (account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
        } else {
            throw new IllegalArgumentException("Insufficient balance");
        }
        return bankAccountRepository.save(account);
    }
}