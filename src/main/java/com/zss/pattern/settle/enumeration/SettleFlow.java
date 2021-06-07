package com.zss.pattern.settle.enumeration;

import java.util.ArrayList;
import java.util.List;

public enum SettleFlow implements BaseEnum{

    broker_export_file(10,"柜台文件导出(给HS)",SettleModel.BEFORE,20),
    trade_import_file(20,"交易所文件导入",SettleModel.BEFORE,30),
    broker_import_file(30,"柜台文件导入(给清算)",SettleModel.BEFORE,40),
    make_deal_check(40,"成交数据配对",SettleModel.BEFORE,50),
    dvp_settlement(50,"DVP交收",SettleModel.BEFORE,60),
    settle_before_stop_business(60,"清算前处理(暂停业务)",SettleModel.BEFORE,70),

    init_copy(70,"设置新副本",SettleModel.PROCESS,80),
    execute_copy(80,"执行副本",SettleModel.PROCESS,90),
    hs_import_check(90,"导入HS结果并核对",SettleModel.PROCESS,100),
    settlement_original(100,"结算到正本",SettleModel.PROCESS,110),

    result_summary(110,"清算结果汇总",SettleModel.SUMMARY,120),
    result_sync_broker(120,"结果同步覆盖",SettleModel.SUMMARY,130),
    result_backup(130,"清算结果备份",SettleModel.SUMMARY,140),
    finish_system_init(140,"系统初始化",SettleModel.SUMMARY,0),

    ;

    private int index;
    private String description;
    private int nextFlow;
    private SettleModel settleModel;

    SettleFlow(int index, String description, SettleModel model, int nextFlow){
        this.index = index;
        this.description = description;
        this.settleModel = model;
        this.nextFlow = nextFlow;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public static SettleFlow getFirstFlow(){
        return broker_export_file;
    }

    public static SettleFlow getLastFlow(){
        return finish_system_init;
    }

    public int getNextFlow() {
        return nextFlow;
    }

    public SettleFlow getNextSettleFlow(){
        if (nextFlow == 0){
            return null;
        }
        for (SettleFlow flow : SettleFlow.values()) {
            if (flow.getIndex() == nextFlow){
                return flow;
            }
        }
        return null;
    }

    public List<SettleFlow> getHigherUpFlow(){
        List<SettleFlow> flowList = new ArrayList<>();
        for (SettleFlow flow : SettleFlow.values()) {
            if (index == flow.getNextFlow()){
                flowList.add(flow);
            }
        }
        return flowList;
    }
}
