package com.zss.pattern.aop.manager;

import com.alibaba.fastjson.JSON;
import com.zss.pattern.aop.manager.hk.HkCalcManager;
import com.zss.pattern.aop.manager.us.UsCalcManager;
import com.zss.result.ModelResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MarketCalcManager {

    @Autowired
    private HkCalcManager hkCalcManager;

    @Autowired
    private UsCalcManager usCalcManager;

    public void runHk(){
        FlowParam param = new FlowParam();
        param.setMarket(1);
        param.setTradeDate(20210628);
        ModelResult<FlowContext> modelResult = hkCalcManager.around(param);
        System.out.println(JSON.toJSONString(modelResult.getModel()));
    }

    public void runUs(){
        FlowParam param = new FlowParam();
        param.setMarket(2);
        param.setTradeDate(20210627);
        ModelResult<FlowContext> modelResult = usCalcManager.around(param);
        System.out.println(JSON.toJSONString(modelResult.getModel()));
    }
}
