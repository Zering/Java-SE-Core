package dubbo.client;

import dubbo.api.HelloDemoI;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhanghaojie on 2018/2/27.
 * <p>
 * 消费方
 */
public class Consumer {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo_demo/consumer.xml");
        context.start();

        HelloDemoI helloService = (HelloDemoI) context.getBean("helloService");
        System.out.println(helloService.sayHello("first dubbo demo"));
    }
}
