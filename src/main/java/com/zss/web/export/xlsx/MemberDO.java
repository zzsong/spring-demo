package com.zss.web.export.xlsx;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.zss.web.export.xlsx.support.LocalDateNumberConverter;

import java.time.LocalDate;

public class MemberDO {
    /**
     * index: 从0开始
     */
    @ExcelProperty(index = 0, value = "姓名")
    private String name;

    @ExcelProperty(index = 1, value = "资产号")
    private long no;

//    @DateTimeFormat("yyyyMMdd")
    @ExcelProperty(index = 2, value = "出生日期", converter = LocalDateNumberConverter.class)
    private LocalDate date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNo() {
        return no;
    }

    public void setNo(long no) {
        this.no = no;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
