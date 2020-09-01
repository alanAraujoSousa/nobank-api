package com.capgemini.nobank.domain.dto.v1;

import com.capgemini.nobank.domain.persistence.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;
import org.javamoney.moneta.Money;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class AccountV1DTO {

    @NotNull
    @Positive
    private Long id;

    @NotNull
    @PositiveOrZero
    @Digits(integer = 14, fraction = 2)
    private BigDecimal moneyAmount;

    @JsonIgnore
    private LocalDateTime createdAt;

    @JsonIgnore
    private LocalDateTime updatedAt;

    @JsonIgnore
    private LocalDateTime deletedAt;

    public static AccountV1DTO toEntity(Account account, ModelMapper modelMapper) {
        return modelMapper.map(account, AccountV1DTO.class);
    }
}
