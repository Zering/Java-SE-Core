package collection.list;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * LinkedList 链表结构，有序，可实现堆栈、队列。双端队列，不同步
 * @author Zhanghj
 *
 */
public class LinkedListDemo {
	
	public static void main(String[] args) {
		
		LinkedList<String> linkedList = new LinkedList<String>();
		
		linkedList.addFirst("First");
		linkedList.offerFirst("Second"); //等价
		linkedList.add("third");
		linkedList.add("third");
		linkedList.add("third");	//可重复
		linkedList.add(null);		//可null
		linkedList.add("forth");
		linkedList.addLast("five");
		linkedList.offerLast("six");	//等价
		
		linkedList.add(3, "insert");
		
		//toString输出
		System.out.println("linkedList.toString() 输出：" + linkedList.toString());
		
		//for循环遍历
		System.out.print("for循环遍历输出：");
		for(int i = 0;i < linkedList.size();i++){
			System.out.print(linkedList.get(i));
		}
		System.out.println();
		
		//Iterator遍历
		System.out.print("Iterator循环遍历输出：");
		Iterator<String> iterator = linkedList.iterator();
		while (iterator.hasNext()) {
			String string = iterator.next();
			System.out.print(string+",");
		}
		System.out.println();
		
		//ListIterator遍历
		System.out.print("ListIterator遍历输出：");
		ListIterator<String> listIterator = linkedList.listIterator();
		while (listIterator.hasNext()) {
			String string = listIterator.next();
			System.out.print(string+",");
			listIterator.add("||");
		}
		System.out.println();
		
		//for each遍历
		for(String string:linkedList){
			System.out.print(string);
		}
		System.out.println();
		
		//其他常用操作
		System.out.println("取头元素："+linkedList.getFirst());
		System.out.println("取尾元素："+linkedList.getLast());
		System.out.println("移除头元素："+linkedList.removeFirst());
		System.out.println("移除尾元素："+linkedList.removeLast());
		System.out.println("默认remove,移除头元素："+linkedList.remove());
		
		//此方法不同步，多线程使用时用：Collections.synchronizedList(new linkedList())
		@SuppressWarnings("unused")
		List<String> synchronizedLinkedList 
				= Collections.synchronizedList(new LinkedList<String>());
	}
	
}
