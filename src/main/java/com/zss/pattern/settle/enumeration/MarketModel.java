package com.zss.pattern.settle.enumeration;

public enum MarketModel implements BaseEnum{

    HK(1,""),
    CN(2,""),
    US(4,""),

    ;

    private int index;
    private String description;

    MarketModel(int index, String description){
        this.index = index;
        this.description = description;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
