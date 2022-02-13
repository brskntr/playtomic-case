package com.playtomic.tests.wallet.service.wallet;

import com.playtomic.tests.wallet.data.entity.WalletEntity;
import com.playtomic.tests.wallet.data.repo.WalletRepository;
import com.playtomic.tests.wallet.exceptions.NotFoundException;
import com.playtomic.tests.wallet.model.TopUpWalletModel;
import com.playtomic.tests.wallet.model.WalletModel;
import com.playtomic.tests.wallet.model.mapper.WalletMapper;
import com.playtomic.tests.wallet.service.BaseService;
import com.playtomic.tests.wallet.service.stripe.StripeService;
import com.playtomic.tests.wallet.service.stripe.StripeServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author bariskantar
 */
@Service
public class WalletService extends BaseService {

    private final Logger log = LoggerFactory.getLogger(WalletService.class);


    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;
    private final StripeService stripeService;

    public WalletService(WalletRepository walletRepository, WalletMapper walletMapper, StripeService stripeService) {
        this.walletRepository = walletRepository;
        this.walletMapper = walletMapper;
        this.stripeService = stripeService;
    }

    public WalletModel create(WalletModel walletModel) {
        WalletEntity walletEntity = walletMapper.map(walletModel);
        walletRepository.save(walletEntity);
        log.info("Wallet created {}", walletEntity);
        return walletMapper.map(walletEntity);
    }

    public WalletModel get(String id) {
        WalletEntity walletEntity = walletRepository.findById(id).orElseThrow(() -> new NotFoundException("Wallet not found"));
        return walletMapper.map(walletEntity);
    }

    public WalletModel topUpWallet(TopUpWalletModel topUpWalletModel) {

        WalletEntity walletEntity = walletRepository.findById(topUpWalletModel.getId()).orElseThrow(() -> new NotFoundException("Wallet not found."));
        try {
            stripeService.charge(topUpWalletModel.getCreditCardNumber(), topUpWalletModel.getAmount());

            walletEntity.setBalance(walletEntity.getBalance().add(topUpWalletModel.getAmount()));
            walletRepository.save(walletEntity);
            return walletMapper.map(walletEntity);
        } catch (StripeServiceException e) {
            log.error("[topUpWallet] An exception occurred", e);
            // throw exception controller advice will handle on controller layer to return clear message for end user
            throw e;
        }

    }
}
