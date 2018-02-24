package java8;

/**
 * Created by zhanghaojie on 2018/2/9.
 * <p>
 * 函数式接口示例
 * jdk中的util.function包下提供了一些常用的接口类以供使用
 */
public class FunctionInterfaceDemo {

    /**
     * 声明一个函数式接口
     *
     * @param <T>
     */
    @FunctionalInterface
    interface Predicate<T> {
        boolean test(T t);
    }


    /**
     * 判定
     *
     * @return
     */
    public static boolean doPredicate(int age, Predicate<Integer> predicate) {
        return predicate.test(age);
    }

    /**
     * 和判定
     *
     * @return
     */
    public static boolean sumPredicate(int x, int y, Predicate<Integer> predicate) {
        return predicate.test(x + y);
    }


    /**
     * 只声明了一个接口，根据不同地方的需求,省去了编写匿名内部类的程序
     *
     * @param args
     */
    public static void main(String[] args) {
        boolean is17Adult = doPredicate(17, (age) -> (age >= 18));
        System.out.println(is17Adult);
        boolean is20 = doPredicate(20, (age) -> (age == 20));
        System.out.println(is20);
        boolean isSum = sumPredicate(10, 5, (preSum) -> (preSum == 10 + 5));
        System.out.println(isSum);
    }

}
