package collection.map;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * LinkedHashMap:在hashmap上，维护一个双重链表，决定迭代顺序（插入顺序），对相同的键多次插入不会影响其位置
 * @author Zhanghj
 *
 */
public class LinkedHashMapDemo {
	
	public static void main(String[] args) {
		LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<String, Integer>();
		
		linkedHashMap.put("first", 1);
		linkedHashMap.put("second", 2);
		linkedHashMap.put("three", 3);
		linkedHashMap.put("four", 4);
		linkedHashMap.put(null, 0);
		linkedHashMap.put("five", 5);
		
		System.out.println(linkedHashMap);
		
		Iterator<Entry<String, Integer>> iterator = linkedHashMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Integer> entry = iterator.next();
			System.out.println("key:"+entry.getKey()+"value:"+entry.getValue());
		}
		
		
		//复制一个已有的任意Map,可以确保copy与原Map完全一致
		Map<String, Integer> copy = new LinkedHashMap<String, Integer>(linkedHashMap);
		System.out.println(copy);
		
		//同步包装
		@SuppressWarnings("unused")
		Map<String,Integer> m = Collections.synchronizedMap(new LinkedHashMap<String,Integer>());
		
		//其他操作参考LinkedHashSet
		
	}
}
