package com.playtomic.tests.wallet.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author bariskantar
 */
@Data
public class WalletModel {
    private String id;
    private String name;
    private BigDecimal balance = BigDecimal.ZERO;
}
