package java8;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by zhanghaojie on 2018/1/31.
 * <p>
 * java8新增的关于集合的一些语法糖
 */
public class CollectionDemo {

    private static List<Integer> alist;

    static {
        alist = Lists.newArrayList();
        for (int i = 1; i < 100; i++) {
            alist.add(i);
        }
    }

    /**
     * java8之前的遍历
     */
    private static void traverseBefore8(List<Integer> list) {
        for (Integer i : list) {
            System.out.print(i + "\t");
        }
    }

    /**
     * java8的遍历
     * 两种方式
     * 1.通用lamda
     * 2.方法引用由::双冒号操作符标示
     */
    private static void traverseBy8(List<Integer> list) {
        list.forEach(i -> System.out.print(i + "\t"));
        // list.forEach(System.out::println);
    }

    public static void main(String[] args) {
        traverseBefore8(alist);
        System.out.println("\n ----分割线-----");
        traverseBy8(alist);
    }

}
