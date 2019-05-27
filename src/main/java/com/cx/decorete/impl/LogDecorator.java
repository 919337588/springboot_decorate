package com.cx.decorete.impl;

import com.cx.decorete.GatewayComponent;
import com.cx.decorete.AbstractDecorator;


public class LogDecorator extends AbstractDecorator {

    public LogDecorator(GatewayComponent gatewayComponent) {
        super(gatewayComponent);
    }

    @Override
    public void service() {
        super.service();
        System.out.println("第二步>>> 网关中新增日志收集..");
    }


}
