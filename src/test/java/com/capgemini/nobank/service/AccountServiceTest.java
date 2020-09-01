package com.capgemini.nobank.service;

import com.capgemini.nobank.domain.dto.v1.CreateAccountV1DTO;
import com.capgemini.nobank.domain.dto.v1.DepositV1DTO;
import com.capgemini.nobank.domain.dto.v1.WithdrawV1DTO;
import com.capgemini.nobank.domain.persistence.Account;
import com.capgemini.nobank.exceptions.ExceededTransactionLowerLimitException;
import com.capgemini.nobank.exceptions.ExceededTransactionUpperLimitException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.AssertionErrors;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class AccountServiceTest {

    @Autowired
    private AccountService service;

    @Test
    public void when_deposit_valid_value() {

        DepositV1DTO depositV1DTO = new DepositV1DTO();
        depositV1DTO.setMoneyAmount(new BigDecimal(100));
        long id = 349244l;
        service.deposit(id, depositV1DTO);

        Account account = service.get(id);
        Assertions.assertThat(account.getMoneyAmount()
                .compareTo(new BigDecimal(100)) > -1);
    }

    @Test
    public void when_deposit_too_large_value() {

        DepositV1DTO depositV1DTO = new DepositV1DTO();
        depositV1DTO.setMoneyAmount(new BigDecimal(999999999999999999l));
        long id = 349244l;
        assertThrows(ExceededTransactionUpperLimitException.class, () -> {
            service.deposit(id, depositV1DTO);
        });
    }

    @Test
    public void when_withdraw_too_large_value() {

        WithdrawV1DTO withdraw = new WithdrawV1DTO();
        withdraw.setMoneyAmount(new BigDecimal(999999999999999999l));
        long id = 349244l;
        assertThrows(ExceededTransactionLowerLimitException.class, () -> {
            service.withdrawal(id, withdraw);
        });
    }
}
