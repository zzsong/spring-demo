package com.zss.web.domain;

import java.util.List;

public class StreamParam extends AuthParam {

    private String filename;
    private List<Integer> category;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public List<Integer> getCategory() {
        return category;
    }

    public void setCategory(List<Integer> category) {
        this.category = category;
    }
}
