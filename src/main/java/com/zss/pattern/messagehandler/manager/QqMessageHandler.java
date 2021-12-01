package com.zss.pattern.messagehandler.manager;

import org.springframework.stereotype.Component;

@Component
public class QqMessageHandler implements MessageHandler{
    @Override
    public void execute(Message message) {

    }

    @Override
    public String getType() {
        return "qq";
    }
}
