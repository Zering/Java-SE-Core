package java8;

/**
 * Created by zhanghaojie on 2018/1/31.
 * <p>
 * lamda说明示例
 * 基本表达式格式
 * expression = (variable) -> action
 */
public class LamdaDemo {


    /**
     * 1.8以前创建一个线程
     */
    public static void originThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("new a thread!");
            }
        }).start();
    }

    /**
     * lamda写法
     */
    public static void baseLamdaThread() {
        new Thread(() -> System.out.println("new a thread!")).start();
    }

}
