package java8;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Created by zhanghaojie on 2018/2/9.
 * <p>
 * util.function包下的工具类示例
 */
public class FunctionUtilDemo {

    /**
     * 消费型接口
     */
    public static void donate(int money, Consumer<Integer> consumer) {
        consumer.accept(money);
    }


    /**
     * 供给型接口
     *
     * @param num
     * @param supplier
     * @return
     */
    public static List<Integer> randomNums(int num, Supplier<Integer> supplier) {
        List<Integer> randomNum = Lists.newArrayList();
        for (int i = 0; i < num; i++) {
            randomNum.add(supplier.get());
        }
        return randomNum;
    }

    /**
     * 函数型接口
     *
     * @param str
     * @param converse
     * @return
     */
    public static int converse(String str, Function<String, Integer> converse) {
        return converse.apply(str);
    }

    /**
     * 断言型接口
     */
    public static void filter(List<Integer> origin, Predicate<Integer> predicate) {
        for (Integer one : origin) {
            if (predicate.test(one)) {
                System.out.println(one);
            }
        }
    }


    public static void main(String[] args) {
        donate(100, money -> System.out.println("捐献：" + money));
        List<Integer> randomNums = randomNums(5, () -> (int) (Math.random() * 100));
        randomNums.forEach(System.out::println);
        String origin = "10";
        int intValue = converse(origin, Integer::parseInt);
        System.out.println(intValue == 10);
        filter(randomNums, (one) -> (one > 10));
    }

}
