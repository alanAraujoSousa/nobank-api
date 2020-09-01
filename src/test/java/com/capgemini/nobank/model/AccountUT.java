package com.capgemini.nobank.model;

import com.capgemini.nobank.domain.dto.v1.CreateAccountV1DTO;
import com.capgemini.nobank.domain.persistence.Account;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountUT {

    private static final ModelMapper modelMapper = new ModelMapper();

    @Test
    public void check_create_mapping() {

        CreateAccountV1DTO creation = new CreateAccountV1DTO();
        creation.setMoneyAmount(new BigDecimal(12));

        Account entity = modelMapper.map(creation, Account.class);
        assertEquals(creation.getMoneyAmount(), entity.getMoneyAmount());
    }
}
