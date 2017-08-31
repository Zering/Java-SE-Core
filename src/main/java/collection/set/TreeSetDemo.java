package collection.set;

import java.util.Collections;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * TreeSet 内部由TreeMap实现（TreeMap的key），有序（自然顺序），tree结构，不同步
 * @author Zhanghj
 *
 */
public class TreeSetDemo {
	
	public static void main(String[] args) {
		TreeSet<String> treeSet = new TreeSet<String>();
		
		treeSet.add("first");
//		treeSet.add(null);  	运行时NullPointerException
		treeSet.add("First");
		treeSet.add("first");	//Set值唯一，不会报错但也只有一个first元素
		treeSet.add("second");
		treeSet.add("Second");
		
		System.out.println(treeSet);
		
		System.out.println(treeSet.descendingSet()); //降序
		
		Iterator<String> iterator = treeSet.iterator();
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			System.out.print(string);
		}
		System.out.println();
		
		Iterator<String> descendingIterator = treeSet.descendingIterator();
		while (descendingIterator.hasNext()) {
			String string = (String) descendingIterator.next();
			System.out.print(string);
		}
		System.out.println();
		
		for (String string : treeSet) {
			System.out.print(string);
		}
		System.out.println();
		
		//其他操作
		System.out.println(treeSet.contains("first"));
		System.out.println(treeSet.first());
		System.out.println(treeSet.last());
		System.out.println("treeSet.ceiling(\"f\"):返回大于等于f的最小元素，没有返回null："+treeSet.ceiling("f"));
		System.out.println("treeSet.floor(\"f\"):返回小于等于f的最大元素，没有返回null："+treeSet.floor("f"));
		System.out.println(treeSet.higher("f"));
		System.out.println(treeSet.lower("f"));
		System.out.println(treeSet.headSet("f"));
		System.out.println(treeSet.subSet("A", "f"));
		System.out.println(treeSet.remove("first"));
		System.out.println(treeSet.pollFirst());
		System.out.println(treeSet.pollLast());
		
		//创建同步的TreeSet
		@SuppressWarnings("unused")
		SortedSet<String> synchronizedTreeSet = Collections.synchronizedSortedSet(new TreeSet<String>());
	}
}
