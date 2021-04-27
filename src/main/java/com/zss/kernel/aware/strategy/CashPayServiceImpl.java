package com.zss.kernel.aware.strategy;

import org.springframework.stereotype.Component;

@Component
public class CashPayServiceImpl implements PayService{
    @Override
    public String getType() {
        return "cash";
    }

    @Override
    public void executePayment() {
        System.out.println(getType()+":\t execute !");
    }
}
