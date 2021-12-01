package com.zss.pattern.messagehandler.manager;

import java.io.Serializable;

public class Message implements Serializable {

    private String header;

    private String body;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
