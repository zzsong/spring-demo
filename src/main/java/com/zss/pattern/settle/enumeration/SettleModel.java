package com.zss.pattern.settle.enumeration;


public enum SettleModel implements BaseEnum{

    BEFORE(1,"", 2),
    PROCESS(2,"", 3),
    SUMMARY(3,"", 0),

    ;

    private int index;
    private String description;
    private int nextModel;

    SettleModel(int index, String description, int model){
        this.index = index;
        this.description = description;
        this.nextModel = model;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public static SettleModel getFirstModel(){
        return BEFORE;
    }

    public SettleModel getNextModel() {
        if (nextModel == 0){
            return null;
        }
        return EnumUtils.getEnum(SettleModel.class, nextModel);
    }
}
