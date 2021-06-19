package com.zss.web.export.xlsx;

import com.alibaba.excel.annotation.ExcelProperty;
import com.zss.web.export.xlsx.support.LocalDateNumberConverter;
import com.zss.web.export.xlsx.support.LocalDateStringConverter;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookDO {

    @ExcelProperty(index = 0,value = "名称")
    private String name;

    @ExcelProperty(index = 2,value = "日期",converter = LocalDateNumberConverter.class)
    private LocalDate date;
    @ExcelProperty(index = 4,value = "发行日期",converter = LocalDateStringConverter.class)
    private LocalDate issueDate;

    @ExcelProperty(index = 1,value = "金额")
    private BigDecimal amount;

    @ExcelProperty(index = 3,value = "价格")
    private double price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }
}
