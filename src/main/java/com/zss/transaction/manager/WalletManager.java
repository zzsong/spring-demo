package com.zss.transaction.manager;

import com.zss.transaction.dao.WalletDao;
import com.zss.transaction.domain.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.SQLException;

@Component
public class WalletManager {

    @Autowired
    private WalletDao walletDao;

    public Wallet query(long id){
        return walletDao.query(id);
    }

    public Wallet queryById(long id){
        return walletDao.queryById(id);
    }

    public void add(long id, BigDecimal money, boolean ex){
        Wallet wallet = walletDao.query(id);
        wallet.setMoney(wallet.getMoney().add(money));
        walletDao.updateMoney(wallet, ex);
    }

    public void subtract(long id, BigDecimal money, boolean ex) {
        Wallet wallet = walletDao.query(id);
        wallet.setMoney(wallet.getMoney().subtract(money));
        walletDao.updateMoney(wallet, ex);
    }
}
