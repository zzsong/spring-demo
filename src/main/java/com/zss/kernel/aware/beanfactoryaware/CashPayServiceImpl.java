package com.zss.kernel.aware.beanfactoryaware;

public class CashPayServiceImpl implements PayService{
    @Override
    public void executePayment() {
        System.out.println("cash payment !");
    }
}
