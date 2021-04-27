package com.zss.kernel.aware.strategy;

import org.springframework.stereotype.Component;

@Component
public class CardPayServiceImpl implements PayService{
    @Override
    public String getType() {
        return "card";
    }

    @Override
    public void executePayment() {
        System.out.println(getType()+":\t execute");
    }
}
