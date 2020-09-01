package com.capgemini.nobank.service;

import com.capgemini.nobank.domain.dto.v1.CreateAccountV1DTO;
import com.capgemini.nobank.domain.dto.v1.DepositV1DTO;
import com.capgemini.nobank.domain.dto.v1.WithdrawV1DTO;
import com.capgemini.nobank.domain.persistence.Account;
import com.capgemini.nobank.exceptions.AccountNotFoundException;
import com.capgemini.nobank.exceptions.ExceededTransactionLowerLimitException;
import com.capgemini.nobank.exceptions.ExceededTransactionUpperLimitException;
import com.capgemini.nobank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    public Account get(Long id) {
        return repository.findById(id).orElseThrow(AccountNotFoundException::new);
    }

    public void deposit(Long id, DepositV1DTO dto) {
        Account account = this.get(id);

        account.setMoneyAmount(account.getMoneyAmount().add(dto.getMoneyAmount()));
        if (account.getMoneyAmount().precision() > 19) {
            throw new ExceededTransactionUpperLimitException();
        }

        repository.save(account);
    }

    public void withdrawal(Long id, WithdrawV1DTO dto) {
        Account account = this.get(id);

        account.setMoneyAmount(account.getMoneyAmount().subtract(dto.getMoneyAmount()));
        if (account.getMoneyAmount().compareTo(BigDecimal.ZERO) < 0) {
            throw new ExceededTransactionLowerLimitException();
        }

        repository.save(account);
    }
}
