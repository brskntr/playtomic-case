package com.playtomic.tests.wallet.data.repo;

import com.playtomic.tests.wallet.data.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author bariskantar
 */
@Repository
public interface WalletRepository extends JpaRepository<WalletEntity,String> {
}
