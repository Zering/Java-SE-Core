package collection.map;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * TreeMap 基于红黑树实现，有序（键的自然顺序，或者自定义Comparator）
 * @author Zhanghj
 *
 */
public class TreeMapDemo {
	public static void main(String[] args) {
		TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>();
		
		treeMap.put("first", 1);
		treeMap.put("two", 2);
		treeMap.put("three", 3);
//		treeMap.put(null, 0);	不可为null，空指针异常
		treeMap.put("foue", 4);
		treeMap.put("five", 5);
		
		System.out.println(treeMap);
		
		Iterator<Entry<String, Integer>> iterator = treeMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Integer> entry = iterator.next();
			System.out.println("key:"+entry.getKey()+" ,value:"+entry.getValue());
		}
		
		//同步包装
		@SuppressWarnings("unused")
		SortedMap<String,Integer> m = Collections.synchronizedSortedMap(new TreeMap<String,Integer>());
		
		//常用操作参考TreeSet
	}
}
