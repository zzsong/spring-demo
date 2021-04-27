package com.zss.kernel.aware.beanfactoryaware;

public class CardPayServiceImpl implements PayService{
    @Override
    public void executePayment() {
        System.out.println("card payment !");
    }
}
