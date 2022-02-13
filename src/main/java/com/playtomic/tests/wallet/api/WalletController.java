package com.playtomic.tests.wallet.api;

import com.playtomic.tests.wallet.api.dto.TopUpWalletDto;
import com.playtomic.tests.wallet.api.dto.WalletDto;
import com.playtomic.tests.wallet.api.resource.WalletResource;
import com.playtomic.tests.wallet.model.TopUpWalletModel;
import com.playtomic.tests.wallet.model.WalletModel;
import com.playtomic.tests.wallet.model.mapper.WalletMapper;
import com.playtomic.tests.wallet.service.wallet.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    private final Logger log = LoggerFactory.getLogger(WalletController.class);

    private final WalletService walletService;
    private final WalletMapper walletMapper;

    public WalletController(WalletService walletService, WalletMapper walletMapper) {
        this.walletService = walletService;
        this.walletMapper = walletMapper;
    }

    @RequestMapping("/")
    void log() {
        log.info("Logging from /");
    }

    @PostMapping("/create")
    public WalletResource createWallet(@RequestBody WalletDto walletDto) {
        WalletModel walletModel = walletMapper.map(walletDto);
        walletModel = walletService.create(walletModel);
        return walletMapper.mapToResource(walletModel);
    }


    @GetMapping("/{walletId}")
    public WalletResource getWallet(@PathVariable(required = false) String walletId) {
        WalletModel walletModel = walletService.get(walletId);
        log.info("Wallet details displayed {}", walletModel);
        return walletMapper.mapToResource(walletModel);
    }

    @PostMapping("/top-up")
    public WalletResource topUpWallet(@RequestBody TopUpWalletDto topUpWalletDto) {
        TopUpWalletModel topUpWalletModel = walletMapper.map(topUpWalletDto);
        WalletModel walletModel = walletService.topUpWallet(topUpWalletModel);
        return walletMapper.mapToResource(walletModel);
    }
}
