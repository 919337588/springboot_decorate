package com.cx.decorete.impl;

import com.cx.decorete.GatewayComponent;
import com.cx.decorete.AbstractDecorator;


public class LimitDecorator extends AbstractDecorator {


    public LimitDecorator(GatewayComponent gatewayComponent) {
        super(gatewayComponent);
    }

    @Override
    public void service() {
//        if (gatewayComponent != null)
//            gatewayComponent.service();
            super.service();
        System.out.println("第三步>>> 网关中新增API接口的限流..");
    }


}
