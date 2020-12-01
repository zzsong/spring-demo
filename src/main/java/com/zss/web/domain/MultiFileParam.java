package com.zss.web.domain;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

public class MultiFileParam {

    private int tradeDate;
    private List<CommonsMultipartFile> file;

    public int getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(int tradeDate) {
        this.tradeDate = tradeDate;
    }

    public List<CommonsMultipartFile> getFile() {
        return file;
    }

    public void setFile(List<CommonsMultipartFile> file) {
        this.file = file;
    }
}
