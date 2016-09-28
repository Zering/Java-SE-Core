package collection.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * HashMap:以Hashing的方式分配键值对的存储位置（bucket），
 * 当hashcode相同时，相同的bucket上以链表的方式存储，get时先根据hashcode再用key.equals来取值
 * 当bucket容量达到加载因子(默认0.75，即75%)的规定时，会将bucket的容量扩大两倍（rehash）
 * 允许null键和null值，无序
 * @author Zhanghj
 *
 */
public class HashMapDemo {

	public static void main(String[] args) {
		//初始化一个容量16，加载因子0.75的空map
		HashMap<String, String> hashMap = new HashMap<String, String>();
		
		hashMap.put("one", "first");
		hashMap.put("two", "second");
		hashMap.put("three", "third");
		hashMap.put(null, null);
		hashMap.put("four", "forth");
		hashMap.put("four", "modify");	//key值唯一，对同一key的put操作相当于更新
		
		//由于hashmap运行null值，所以判断键值null时，用containsKey方法来判断
		System.out.println(hashMap.containsKey(null));
		
		System.out.println(hashMap);
		
		Iterator<Entry<String, String>> iterator = hashMap.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			System.out.println("key:"+entry.getKey()+" ,value:"+entry.getValue());
		}
		
		System.out.println(hashMap.remove("tree"));
		System.out.println(hashMap.replace("two", "another second"));
		System.out.println(hashMap.remove("one", "first"));
		System.out.println(hashMap.replace("four", "modify", "another four"));
		
		System.out.println(hashMap);
		
		//hashmap线程不安全，多线程中使用需要进行包装，或者使用hashtable/concurrentHashMap
		@SuppressWarnings("unused")
		Map<String, String> synchronizedHashMap = Collections.synchronizedMap(new HashMap<String, String>());
	}
	
}
