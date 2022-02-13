package com.playtomic.tests.wallet.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author bariskantar
 */
@Data
public class TopUpWalletModel {
    private String id;
    private BigDecimal amount;
    private String creditCardNumber;
}
