package com.capgemini.nobank.domain.dto.v1;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.javamoney.moneta.Money;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
public class CreateAccountV1DTO {

    @NotNull
    @PositiveOrZero
    @Digits(integer = 14, fraction = 2)
    private BigDecimal moneyAmount;

}
