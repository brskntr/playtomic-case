package com.playtomic.tests.wallet.controller;

import com.playtomic.tests.wallet.api.WalletController;
import com.playtomic.tests.wallet.api.resource.WalletResource;
import com.playtomic.tests.wallet.model.WalletModel;
import com.playtomic.tests.wallet.model.mapper.WalletMapper;
import com.playtomic.tests.wallet.service.wallet.WalletService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author bariskantar
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(WalletController.class)
public class WalletControllerTest {

    @MockBean
    WalletService walletService;

    @MockBean
    WalletMapper walletMapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void test_retrieveWallet() throws Exception {
        WalletModel walletModel = new WalletModel();
        String walletId = UUID.randomUUID().toString();
        walletModel.setId(walletId);
        walletModel.setName("wallet_name");
        walletModel.setBalance(BigDecimal.ZERO);

        Mockito.when(walletService.get(walletId)).thenReturn(walletModel);

        WalletResource walletResource = new WalletResource();
        walletResource.setId(walletId);
        walletResource.setName("wallet_name");
        walletResource.setBalance(BigDecimal.ZERO);
        Mockito.when(walletMapper.mapToResource(walletModel)).thenReturn(walletResource);

        mockMvc.perform(get("/wallet/"+walletId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(walletId)));

    }
}
