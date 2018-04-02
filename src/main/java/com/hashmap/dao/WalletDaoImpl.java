package com.hashmap.dao;

import com.hashmap.entity.user.WalletEntity;
import com.hashmap.models.Wallet;
import com.hashmap.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Component
public class WalletDaoImpl implements WalletDao {

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public BigDecimal totalBalanceInWallet(UUID userId) {
        Optional<WalletEntity> optionalWallet = walletRepository.findById(userId);
        return optionalWallet.map(WalletEntity::getTotalBalance).orElse(null);
    }

    @Override
    public BigDecimal updateBalance(UUID userId, BigDecimal amount) {
        Optional<WalletEntity> optionalWallet = walletRepository.findById(userId);

        optionalWallet.ifPresent(walletEntity -> walletEntity.setTotalBalance(amount));
        return walletRepository.save(optionalWallet.get()).getTotalBalance();
    }

    @Override
    public Wallet save(Wallet wallet) {
        WalletEntity walletEntity = walletRepository.save(new WalletEntity(wallet));
        return walletEntity.toData();
    }

    @Override
    public Optional<Wallet> getWallet(UUID userId) {

        Optional<WalletEntity> walletEntity = walletRepository.findById(userId);

        if(walletEntity.isPresent()){
            return Optional.of(walletEntity.get().toData());
        }
        else
            return Optional.empty();
    }
}
