package com.zss.pattern.settle.enumeration;

public class EnumUtils {

    public static <E extends BaseEnum> E getEnum(Class<E> type, int index){
        E[] enums = type.getEnumConstants();
        for (E e : enums){
            if (e.getIndex() == index){
                return e;
            }
        }
        throw new IllegalArgumentException("不能映射["+index+"]到枚举["+type.getSimpleName()+"]");
    }
}
