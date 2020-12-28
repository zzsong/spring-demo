package com.zss.kernel.proxy.staticproxy;

public class ProxyClient {

    public static void main(String[] args) {
        Target target = new ProxyTarget();
        target.myRequest();
    }
}
