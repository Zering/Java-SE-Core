package java8;

import javax.swing.*;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by zhanghaojie on 2018/2/28.
 * <p>
 * java8的10个示例
 */
public class SummaryDemo {

    /**
     * 1. 用lambda表达式实现Runnable
     */
    private static void newThread() {
        new Thread(() -> System.out.println("创建一个线程")).start();
    }


    /**
     * 2. 使用Java 8 lambda表达式进行事件处理  (swing)
     */
    private static void handleEvent(JButton show) {
        show.addActionListener((e) -> System.out.println("swing中进行时间处理"));
    }

    /**
     * 3. 使用lambda表达式对列表进行迭代
     */
    private static void traverseCollection(List<Object> feature) {
        feature.forEach(one -> System.out.print(" " + one + " "));
        System.out.println();
    }

    /**
     * 4. 使用lambda表达式和函数式接口Predicate
     * 5. 在lambda表达式中加入Predicate
     */
    private static void filter(List<String> names) {
        Predicate<String> startWithJ = one -> one.startsWith("J");
        Predicate<String> length4 = one -> one.length() >= 4;
        // 与
        names.stream()
                .filter(startWithJ.and(length4))
                .forEach(name -> System.out.println(name + " is startWith 'J' and length >= 4"));
        // 或
        names.stream()
                .filter(startWithJ.or(length4))
                .forEach(name -> System.out.println(name + " is startWith 'J' or length >= 4"));
    }


    /**
     * 6. Java 8中使用lambda表达式的Map和Reduce
     */
    private static void mapAndReduce(List<Integer> costs) {
        // map:对每个值进行处理,本例中 每个值增加0.12
        costs.stream().map(cost -> cost + cost * .12).forEach(System.out::println);
        // reduce:接受多个值 返回一个值
        double allBill = costs.stream().map(cost -> cost + cost * .12).reduce((sum, cost) -> sum += cost).get();
        System.out.println(allBill);
    }

    /**
     * 7. 通过过滤创建一个String列表
     */
    private static List<String> filterList(List<String> names) {
        Predicate<String> startWithJ = one -> one.startsWith("J");
        Predicate<String> length4 = one -> one.length() >= 4;
        return names.stream()
                .filter(startWithJ.and(length4)).collect(Collectors.toList());
    }

    /**
     * 8. 对列表的每个元素应用函数
     */
    private static List<String> toUpperCase(List<String> names) {
//        return names.stream().map(name -> name.toUpperCase()).collect(Collectors.toList());
        return names.stream().map(String::toUpperCase).collect(Collectors.toList());
    }

    /**
     * 9. 复制不同的值，创建一个子列表
     */
    private static List<String> distinct(List<String> names) {
        return names.stream().distinct().collect(Collectors.toList());
    }

    /**
     * 10. 计算集合元素的最大值、最小值、总和以及平均值
     */
    private static void statistics(List<Object> numbers) {
        IntSummaryStatistics stat = numbers.stream().mapToInt(x -> (Integer) x).summaryStatistics();
        System.out.println("最大值：" + stat.getMax());
        System.out.println("最小值：" + stat.getMin());
        System.out.println("总和：" + stat.getSum());
        System.out.println("平均值：" + stat.getAverage());
    }

    public static void main(String[] args) {
        List names = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        traverseCollection(names);
        filter(names);
        traverseCollection(names);

        System.out.println("------------------------");

        List costs = Arrays.asList(100, 200, 300, 400, 500);
        traverseCollection(costs);
        mapAndReduce(costs);
        traverseCollection(costs);

        System.out.println("------------------------");

        List acceptList = filterList(names);
        traverseCollection(names);
        System.out.println("####");
        traverseCollection(acceptList);

        System.out.println("------------------------");

        List upperCase = toUpperCase(names);
        traverseCollection(names);
        System.out.println("####");
        traverseCollection(upperCase);

        System.out.println("------------------------");

        List numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        List distinct = distinct(numbers);
        traverseCollection(numbers);
        System.out.println("####");
        traverseCollection(distinct);

        System.out.println("------------------------");

        statistics(numbers);

        System.out.println("------------------------");

        statistics(costs);
    }
}
