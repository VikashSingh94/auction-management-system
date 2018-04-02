package com.hashmap.entity.user;


import com.hashmap.models.Wallet;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "wallet")
public class WalletEntity {

    @Id
    private UUID userId;
    private BigDecimal totalBalance;

    public WalletEntity() {
        totalBalance = new BigDecimal(0);
    }

    public WalletEntity(Wallet wallet){
        this.userId = wallet.getUserId();
        this.totalBalance = wallet.getTotalBalance();
    }

    public Wallet toData(){
        return new Wallet(this.userId,this.totalBalance);

    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }
}
