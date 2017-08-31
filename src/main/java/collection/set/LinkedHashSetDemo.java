package collection.set;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * LinkedHashSet hashcode决定存储位置，另外维护一个双重链表来决定次序（插入顺序）
 * 本质是在HashSet的基础上再维护一个双重链表,用法上与HashSet一致
 * @author Zhanghj
 *
 */
public class LinkedHashSetDemo {

	public static void main(String[] args) {
		/*
		 * 初始化一个空的LinkedHashSet
		 * 一种常用的用法是 new LinkedHashSet(Set s)  因为获得的副本可以保持s的顺序
		 */
		Set<String> linkedHashSet = new LinkedHashSet<String>();
		
		linkedHashSet.add("first");
		linkedHashSet.add(null);
		linkedHashSet.add("Second");
		linkedHashSet.add("third");
		linkedHashSet.add("first");  //不会插入重复的元素，也不会改变原元素在set中的顺序
		linkedHashSet.add("forth");
		
		System.out.println(linkedHashSet);
		
		Iterator<String> iterator = linkedHashSet.iterator();
		while (iterator.hasNext()) {
			String string = (String) iterator.next();
			System.out.print(string);
		}
		System.out.println();
		
		for (String string : linkedHashSet) {
			System.out.print(string);
		}
		System.out.println();
		
		//实现同步的包装方法
		@SuppressWarnings("unused")
		Set<String> s = Collections.synchronizedSet(new LinkedHashSet<String>());
		
		//可参考HashSetDemo
	}
}
