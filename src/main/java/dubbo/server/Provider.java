package dubbo.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by zhanghaojie on 2018/2/27.
 *
 * 服务启动类
 */
public class Provider {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo_demo/provider.xml");
        context.start();

        System.in.read();// 保持运行
    }
}
