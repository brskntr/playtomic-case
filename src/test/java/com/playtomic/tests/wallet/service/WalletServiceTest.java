package com.playtomic.tests.wallet.service;

import com.playtomic.tests.wallet.data.entity.WalletEntity;
import com.playtomic.tests.wallet.data.repo.WalletRepository;
import com.playtomic.tests.wallet.model.WalletModel;
import com.playtomic.tests.wallet.model.mapper.WalletMapper;
import com.playtomic.tests.wallet.service.wallet.WalletService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author bariskantar
 */
@ExtendWith(MockitoExtension.class)
public class WalletServiceTest {

    @InjectMocks
    WalletService walletService;

    @Mock
    WalletRepository walletRepository;

    @Mock
    WalletMapper walletMapper;

    @Test
    public void test_RetrieveWallet() {
        String walletId = UUID.randomUUID().toString();
        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setId(walletId);
        walletEntity.setName("wallet_name");
        walletEntity.setBalance(BigDecimal.ZERO);

        when(walletRepository.findById(walletId)).thenReturn(Optional.of(walletEntity));

        WalletModel walletModel = new WalletModel();
        walletModel.setId(walletId);
        walletModel.setName("wallet_name");
        walletModel.setBalance(BigDecimal.ZERO);
        Mockito.when(walletMapper.map(walletEntity)).thenReturn(walletModel);


        //test
        WalletModel walletModelReturned = walletService.get(walletId);

        assertEquals(walletModelReturned.getId(), walletModel.getId());
        verify(walletRepository, times(1)).findById(walletId);
    }
}
