package com.capgemini.nobank.rest.v1;

import com.capgemini.nobank.constants.EndPointsV1;
import com.capgemini.nobank.domain.dto.v1.AccountV1DTO;
import com.capgemini.nobank.domain.dto.v1.CreateAccountV1DTO;
import com.capgemini.nobank.domain.dto.v1.DepositV1DTO;
import com.capgemini.nobank.domain.dto.v1.WithdrawV1DTO;
import com.capgemini.nobank.domain.persistence.Account;
import com.capgemini.nobank.repository.AccountRepository;
import com.capgemini.nobank.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
@RestController
@RequestMapping(EndPointsV1.ACCOUNT.ROOT)
@RequiredArgsConstructor
public class AccountV1Resource {

    private final AccountService service;
    private final ModelMapper modelMapper;

    @GetMapping(EndPointsV1.ACCOUNT.GET)
    public ResponseEntity get(@PathVariable("id") Long id) {

        Account account = this.service.get(id);
        return ResponseEntity.ok(AccountV1DTO.toEntity(account, modelMapper));
    }

    @PostMapping(EndPointsV1.ACCOUNT.DEPOSIT)
    public ResponseEntity deposit(@PathVariable("id") Long id, @Validated @RequestBody DepositV1DTO dto) {

        this.service.deposit(id, dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping(EndPointsV1.ACCOUNT.WITHDRAW)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity withdraw(@PathVariable("id") Long id, @Validated @RequestBody WithdrawV1DTO dto) {

        this.service.withdrawal(id, dto);
        return ResponseEntity.ok().build();
    }
}
