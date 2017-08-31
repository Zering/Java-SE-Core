package collection.set;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * HashSet 内部由HashMap实现，可null，无序，无重复，不同步
 * @author Zhanghj
 *
 */
public class HashSetDemo {
	
	public static void main(String[] args) {
		//初始容量为16，加载因子0.75
		Set<String> hSet = new HashSet<String>();
		hSet.add("first");
		hSet.add("Second");
		hSet.add("Second Repeat");
		hSet.add("Second Repeat");
		hSet.add("Second Repeat");
		hSet.add(null);
		
		System.out.println(hSet.toString());
		
		Iterator<String> iterator = hSet.iterator();
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			System.out.print(string);
		}
		System.out.println();
		
		for (String string : hSet) {
			System.out.print(string);
		}
		System.out.println();
		
		System.out.println("转数组："+hSet.toArray());
		System.out.println("是否包含某元素："+hSet.contains("Second"));
		System.out.println("移除某个元素："+hSet.remove("Second"));
		
		//线程同步的方式，Collections.synchronizedSet(new HashSet)
		@SuppressWarnings("unused")
		Set<String> synchronizedHashSet = Collections.synchronizedSet(new HashSet<String>());
		
	}
}
