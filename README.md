# springboot_decorate
网关拦截控制_装饰者模式
装饰者模式
什么是装饰者模式
在不改变原有对象的基础上附加功能，相比生成子类更灵活。

装饰者模式应用场景
动态的给一个对象添加或者撤销功能。
装饰者模式优缺点
优点：可以不改变原有对象的情况下动态扩展功能，可以使扩展的多个功能按想要的顺序执行，以实现不同效果。
缺点：更多的类，使程序复杂

装饰者模式类图
 
装饰者模式定义
（1）抽象组件:定义一个抽象接口，来规范准备附加功能的类
（2）具体组件：将要被附加功能的类，实现抽象构件角色接口 
（3）抽象装饰者：持有对具体构件角色的引用并定义与抽象构件角色一致的接口
（4）具体装饰：实现抽象装饰者角色，负责对具体构件添加额外功能。
protected ComponentGateway componentGateway;
setComponentGateway(ComponentGateway componentGateway)

装饰者代码实现
定义一个抽象的接口

public abstract class GatewayComponent {
    public abstract void service();
}

定义被装饰角色
public class BasicComponentGateway extends GatewayComponent {
    public void service() {
        System.out.println("网关开始获取基本参数信息");
    }
}

定义抽象装饰角色
public abstract class AbstractDecorator extends GatewayComponent {
    protected GatewayComponent componentGateway;

    public AbstractDecorator() {

    }

    public AbstractDecorator(GatewayComponent componentGateway) {
        this.componentGateway = componentGateway;
    }

    public void service() {
        componentGateway.service();
    }

    public void setComponentGateway(GatewayComponent componentGateway) {
        if (componentGateway != null)
            this.componentGateway = componentGateway;
    }
}



定义具体装饰角色
public class LogDecorator extends AbstractDecorator {
    public LogDecorator() {

    }

    public LogDecorator(GatewayComponent componentGateway) {
        super(componentGateway);
    }

    @Override
    public void service() {
        // 调用装饰类service
        super.service();
        // 日志收集
        System.out.println("第二步>>>>日志的采集.....");
    }

public class LimitDecorator extends AbstractDecorator {
    public LimitDecorator() {

    }

    public LimitDecorator(GatewayComponent componentGateway) {
        super(componentGateway);
    }


    @Override
    public void service() {
        // 1.传递日志收集装饰类
        System.out.println(super.getClass().toString());
        super.service();
        System.out.println("第三步>>>>API接口限流");
    }


}

}





使用工厂获取装饰类

public class FactoryGateway {
    @Autowired
    private DecoratorMapper decoratorMapper;

    public GatewayComponent getComponentGateway() {
        LimitDecorator limitDecorator = new LimitDecorator();
        LogDecorator logDecorator = new LogDecorator();
        limitDecorator.setComponentGateway(logDecorator);
        logDecorator.setComponentGateway(new BasicComponentGateway());
        return limitDecorator;
    }
}


另外一种方式
//        ComponentGateway componentGateway = new LimitDecorator(new LogDecorator(new BasicComponentGateway()));
//        componentGateway.service();


public class FactoryGateway {

    public static GatewayComponent getGatewayComponent() {
        // 1.新增日志收集
        LogDecorator LogDecorator = new LogDecorator();
        // 2.新增Api接口限流
        LimitDecorator limitDecorator = new LimitDecorator();
        limitDecorator.setGatewayComponent(LogDecorator);
        // 3.创建基本网关获取参数功能
        BasicComponentGateway basicComponentGateway = new BasicComponentGateway();
        LogDecorator.setGatewayComponent(basicComponentGateway);
        return limitDecorator;
    }

    public static void main(String[] args) {
        GatewayComponent gatewayComponent = FactoryGateway.getGatewayComponent();
        gatewayComponent.service();
    }
}

源码角度分析装饰者模式
Java I/O 中的装饰者模式
Spring Session 中的装饰者模式
Mybatis 缓存中的装饰者模式
new FileInputStream(new File())
Java I/O 中的装饰者模式
它基于字符流(InputStream/OutputStream) 和 字节流(Reader/Writer)作为基类，下面画出InputStream、Reader的

抽象构造角色   Reader
FilterReader  抽象的装饰类

 

责任链与装饰模式区别
责任链实现原理
每个被调用者 都持有下一个 被调用者 的引用，客户端只需要发起一次调用即可。
装饰的实现原理
持有被装饰的对象，并具备  被装饰者  的行为，对其行为进行补充增强

csdn地址：https://mp.csdn.net/postedit?not_checkout=1
