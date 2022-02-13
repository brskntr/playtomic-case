package com.playtomic.tests.wallet.api.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author bariskantar
 */
@Data
public class TopUpWalletDto {
    private String id;
    private BigDecimal amount;
    private String creditCardNumber;
}
