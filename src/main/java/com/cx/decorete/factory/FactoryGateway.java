package com.cx.decorete.factory;

import com.cx.decorete.GatewayComponent;
import com.cx.decorete.impl.BasicComponentGateway;
import com.cx.decorete.impl.LimitDecorator;
import com.cx.decorete.impl.LogDecorator;



public class FactoryGateway {

    //    public static GatewayComponent getGatewayComponent() {
//        // 1.创建日志装饰类
//        LogDecorator logDecorator = new LogDecorator();
//        LimitDecorator limitDecorator = new LimitDecorator();
//        limitDecorator.setGatewayComponent(logDecorator);
//        // 2.创建被装饰实现类
//        BasicComponentGateway basicComponentGateway = new BasicComponentGateway();
//        logDecorator.setGatewayComponent(basicComponentGateway);
//        return limitDecorator;
//    }
    public static GatewayComponent getGatewayComponent() {
        return new LimitDecorator(new LogDecorator(new BasicComponentGateway()));
    }

    public static void main(String[] args) {
        GatewayComponent gatewayComponent = FactoryGateway.getGatewayComponent();
        gatewayComponent.service();
    }

}
