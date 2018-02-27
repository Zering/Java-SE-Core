package dubbo.server;

import dubbo.api.HelloDemoI;

/**
 * Created by zhanghaojie on 2018/2/26.
 *
 * 服务方提供的实现
 */
public class HelloDemo implements HelloDemoI {


    @Override
    public String sayHello(String name) {
        return "Hello, " + name;
    }

}
