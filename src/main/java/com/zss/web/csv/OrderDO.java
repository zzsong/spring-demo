package com.zss.web.csv;

import com.opencsv.bean.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @CsvBindByName(column = "订单ID")
    @CsvBindByPosition(position = 1)
    private long id;

    @CsvBindByName(column = "订单编号")
    @CsvBindByPosition(position = 0)
    private String orderNo;

    @CsvBindByName(column = "下单时间")
    @CsvDate("MM-dd HH:mm:ss")
    @CsvBindByPosition(position = 4)
    private LocalDateTime orderTime;

    @CsvBindByName(column = "下单日期")
    @CsvDate("yyyy-MM-dd")
    @CsvBindByPosition(position = 3)
    private LocalDate occurDate;

    @CsvBindByName(column = "金额")
    @CsvNumber(value = "000.###")
    @CsvBindByPosition(position = 2)
    private BigDecimal amount;

    @CsvBindByName(column = "名称")
    @CsvBindAndSplitByPosition(position = 5, writeDelimiter = "-", collectionType = List.class, elementType = String.class)
    private List<String> nameList;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public LocalDate getOccurDate() {
        return occurDate;
    }

    public void setOccurDate(LocalDate occurDate) {
        this.occurDate = occurDate;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<String> getNameList() {
        return nameList;
    }

    public void setNameList(List<String> nameList) {
        this.nameList = nameList;
    }
}
