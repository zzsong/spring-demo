package com.zss.web.domain;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UploadParam {

    private int tradeDate;
    private CommonsMultipartFile file;

    public int getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(int tradeDate) {
        this.tradeDate = tradeDate;
    }

    public CommonsMultipartFile getFile() {
        return file;
    }

    public void setFile(CommonsMultipartFile file) {
        this.file = file;
    }
}
