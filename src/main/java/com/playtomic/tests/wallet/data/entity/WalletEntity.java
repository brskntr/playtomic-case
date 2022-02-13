package com.playtomic.tests.wallet.data.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * @author bariskantar
 */

@Data
@Entity(name = "wallet")
public class WalletEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "balance")
    private BigDecimal balance;
}
