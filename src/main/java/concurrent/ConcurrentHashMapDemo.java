package concurrent;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap支持获取的完全并发和更新的所期望可调整并发的哈希表。
 * 与HashTable的作用类似，HashTable是锁住整个hash表，ConcurrentHashMap采取分段锁(默认16段)的方式提高效率
 * 
 * @author Zhanghj
 *
 */
public class ConcurrentHashMapDemo {

	public static void main(String[] args) {
		//创建一个带有默认初始容量 (16)、加载因子 (0.75) 和 concurrencyLevel (16) 的新的空映射。
		ConcurrentHashMap<String, Integer> concurrentHashMap = 
											new ConcurrentHashMap<String, Integer>();
		
		concurrentHashMap.put("first", 1);
		concurrentHashMap.put("second", 2);
		concurrentHashMap.put("third", 3);
//		concurrentHashMap.put(null, 0);
		concurrentHashMap.put("forth", 4);
		
		for (Entry<String, Integer> entry : concurrentHashMap.entrySet()) {
			System.out.println("Entry:"+entry.getKey()+"="+entry.getValue());
		}
	}
}
