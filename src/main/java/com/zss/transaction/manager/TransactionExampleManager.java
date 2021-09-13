package com.zss.transaction.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Component
public class TransactionExampleManager {

    @Autowired
    private WalletManager walletManager;

    @Autowired
    private TransactionTemplate transactionTemplate;

    /**
     *
     */
    @Transactional
    public void example1(){
        BigDecimal amount = BigDecimal.valueOf(20);
        walletManager.add(1,amount,false);
        walletManager.subtract(2,amount,true);
    }

    public void example2(){
        example2_private();
    }

    /**
     * @see org.springframework.transaction.interceptor.AbstractFallbackTransactionAttributeSource
     * computeTransactionAttribute
     * // Don't allow no-public methods as required.
     * 		if (allowPublicMethodsOnly() && !Modifier.isPublic(method.getModifiers())) {
     * 			return null;
     *       }
     */
    @Transactional
    private void example2_private(){
        BigDecimal amount = BigDecimal.valueOf(20);
        walletManager.add(1,amount,false);
        walletManager.subtract(2,amount,true);
    }

    public void example3(){

        transactionTemplate.execute((status -> {

            return null;
        }));
    }
}
