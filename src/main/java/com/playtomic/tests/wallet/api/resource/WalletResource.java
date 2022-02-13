package com.playtomic.tests.wallet.api.resource;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author bariskantar
 */
@Data
public class WalletResource {
    private String id;
    private String name;
    private BigDecimal balance;
}
