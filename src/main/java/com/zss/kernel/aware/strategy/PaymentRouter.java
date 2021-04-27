package com.zss.kernel.aware.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PaymentRouter {

    private Map<String, PayService> payServiceMap = new ConcurrentHashMap<>();

    @Autowired
    private List<PayService> payServiceList;

    public PayService getPayService(String type){
        return payServiceMap.get(type);
    }

    @PostConstruct
    public void init(){
        System.out.println("init....");
        for (PayService service : payServiceList){
            payServiceMap.put(service.getType(), service);
        }
    }
}
