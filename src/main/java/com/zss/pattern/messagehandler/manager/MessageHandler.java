package com.zss.pattern.messagehandler.manager;

public interface MessageHandler<T extends Message> {

    void execute(T message);

    String getType();
}
