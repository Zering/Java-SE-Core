package java8;

import java8.domain.Range;
import java8.util.Ranges;

import java.util.LinkedHashMap;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * Created by zhanghaojie on 2018/3/1.
 * <p>
 * java8随机数
 */
public class RandomDemo {

    /**
     * 生成n个随机数并打印
     */
    private static void generateRandom(int min, int max, int n) {
        Random random = new Random();
        random.ints(min, max).limit(n).forEach(System.out::println);
    }


    /**
     * 高斯伪随机数统计
     */
    private static void gaussianRandom() {
        Random random = new Random();
        DoubleStream doubleStream = Stream.generate(random::nextGaussian).mapToDouble(e -> e);

        LinkedHashMap<Range, Integer> rangeInfo = doubleStream.filter(e -> e > -1 && e < 1)
                .limit(1000000)
                .boxed()
                .map(Ranges::of)
                .collect(Ranges::emptyRangeCountMap, (m, v) -> m.put(v, m.get(v) + 1), Ranges::mergeRangeCountMaps);

        rangeInfo.forEach((k, v) -> System.out.println(k + "\t 数量: " + v));

    }


    public static void main(String[] args) {

        generateRandom(1, 10, 20);

        System.out.println("----------------------");

        gaussianRandom();
    }
}


