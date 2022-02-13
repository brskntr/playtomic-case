package com.playtomic.tests.wallet.model.mapper;

import com.playtomic.tests.wallet.api.dto.TopUpWalletDto;
import com.playtomic.tests.wallet.api.dto.WalletDto;
import com.playtomic.tests.wallet.api.resource.WalletResource;
import com.playtomic.tests.wallet.data.entity.WalletEntity;
import com.playtomic.tests.wallet.model.TopUpWalletModel;
import com.playtomic.tests.wallet.model.WalletModel;
import org.mapstruct.Mapper;

/**
 * @author bariskantar
 */

@Mapper(componentModel = "spring")
public interface WalletMapper {

    WalletModel map(WalletDto walletDto);
    WalletEntity map(WalletModel walletModel);
    WalletModel map(WalletEntity walletEntity);

    WalletResource mapToResource(WalletModel walletModel);

    TopUpWalletModel map(TopUpWalletDto topUpWalletDto);

}
