package com.cx.decorete;


public class AbstractDecorator extends GatewayComponent {
    public AbstractDecorator(GatewayComponent gatewayComponent) {
        this.gatewayComponent = gatewayComponent;
    }

    public GatewayComponent gatewayComponent;

    public void service() {
        if (gatewayComponent != null)
            gatewayComponent.service();
    }

//    public void setGatewayComponent(GatewayComponent gatewayComponent) {
//        this.gatewayComponent = gatewayComponent;
//    }
}
