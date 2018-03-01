package java8.util;

import java8.domain.Range;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zhanghaojie on 2018/3/1.
 *
 * 范围统计工具类
 */
public class Ranges {
    private static LinkedHashMap<Integer, Range> rangeMap = new LinkedHashMap<>();

    static {
        rangeMap.put(-10, new Range(-1.0, -0.9));
        rangeMap.put(-9, new Range(-0.9, -0.8));
        rangeMap.put(-8, new Range(-0.8, -0.7));
        rangeMap.put(-7, new Range(-0.7, -0.6));
        rangeMap.put(-6, new Range(-0.6, -0.5));
        rangeMap.put(-5, new Range(-0.5, -0.4));
        rangeMap.put(-4, new Range(-0.4, -0.3));
        rangeMap.put(-3, new Range(-0.3, -0.2));
        rangeMap.put(-2, new Range(-0.2, -0.1));
        rangeMap.put(-1, new Range(-0.1, 0.0));
        rangeMap.put(0, new Range(0.0, 0.1));
        rangeMap.put(1, new Range(0.1, 0.2));
        rangeMap.put(2, new Range(0.2, 0.3));
        rangeMap.put(3, new Range(0.3, 0.4));
        rangeMap.put(4, new Range(0.4, 0.5));
        rangeMap.put(5, new Range(0.5, 0.6));
        rangeMap.put(6, new Range(0.6, 0.7));
        rangeMap.put(7, new Range(0.7, 0.8));
        rangeMap.put(8, new Range(0.8, 0.9));
        rangeMap.put(9, new Range(0.9, 1.0));
    }

    public static Range of(double d) {
        int key = (int) Math.floor(d * 10);
        return rangeMap.get(key);
    }

    public static LinkedHashMap<Range, Integer> emptyRangeCountMap() {
        LinkedHashMap<Range, Integer> rangeCountMap = new LinkedHashMap<>();
        for (Range range : rangeMap.values()) {
            rangeCountMap.put(range, 0);
        }
        return rangeCountMap;
    }

    public static void mergeRangeCountMaps(Map<Range, Integer> map1, Map<Range, Integer> map2) {
        for (Range range : rangeMap.values()) {
            map1.put(range, map1.get(range) + map2.get(range));
        }
    }
}
