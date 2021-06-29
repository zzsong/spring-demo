package com.zss.pattern.aop.manager.hk;

import com.zss.pattern.aop.manager.AbstractAspectManager;
import com.zss.pattern.aop.manager.FlowContext;
import com.zss.pattern.aop.manager.FlowParam;
import com.zss.result.BaseResult;
import com.zss.result.ModelResult;
import org.springframework.stereotype.Component;

@Component
public class HkCalcManager extends AbstractAspectManager<FlowParam, FlowContext> {

    @Override
    protected BaseResult before() {
        BaseResult baseResult = new BaseResult();
        System.out.println("hk before");

        return baseResult;
    }

    @Override
    protected ModelResult<FlowContext> process(FlowParam flowParam) {
        ModelResult<FlowContext> modelResult = new ModelResult<>();
        FlowContext context = new FlowContext();
        context.setMarket(flowParam.getMarket());
        context.setTradeDate(flowParam.getTradeDate());
        context.setNotes("hk process");
        modelResult.setModel(context);
        return modelResult;
    }

    @Override
    protected BaseResult after() {
        BaseResult baseResult = new BaseResult();
        System.out.println("hk after");
        return baseResult;
    }
}
