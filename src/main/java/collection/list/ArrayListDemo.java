package collection.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

/**
 * ArrayList 有序（添加顺序），可重复可null，不同步，可变长度的数组
 * @author Zhanghj
 *
 */
public class ArrayListDemo {

	public static void main(String[] args) {

		//构造一个初始容量为 10 的空列表，自动的扩容和减容
		List<String> list = new ArrayList<>();

		list.add("first");
		list.add("Second");
		list.add("Second Repeat");
		list.add("Second Repeat");
		list.add("Second Repeat");
		list.add(null);
		list.add("after null");

		//toString 输出
		System.out.println("list.toString() 输出："+list.toString());

		//循环遍历
		System.out.print("for循环遍历输出：");
		for (String aList : list) {
			System.out.print(aList + ",");
		}
		System.out.println();

		//Iterator遍历
		System.out.print("Iterator遍历输出：");
		for (String string : list) {
			System.out.print(string + ",");
		}
		System.out.println();


		//ListIterator遍历，在Iterator基础上添加了add(),previous()和hasPrevious()方法
		System.out.print("ListIterator遍历输出：");
		ListIterator<String> listIterator = list.listIterator();
		while (listIterator.hasNext()) {
			String string = listIterator.next();
			System.out.print(string);
			listIterator.add("|");
		}
		System.out.println();


		//for each遍历
		System.out.print("for each遍历输出：");
		for(String str:list){
			System.out.print(str+",");
		}
		System.out.println();

		//其他常用操作
		System.out.println("是否包含first:"+list.contains("first"));
		System.out.println("移除first:"+list.remove("first"));
		System.out.println("是否包含first:"+list.contains("first"));
		System.out.println("下标方式移除下标为0的元素："+list.remove(0));
		System.out.println("返回某个元素的下标，查找的第一个元素："+list.indexOf("Second Repeat"));
		System.out.println("返回最后一个指定元素的下标："+list.lastIndexOf("Second Repeat"));
		System.out.println("判断是否为空：" + list.isEmpty());

		//创建一个同步的ArrayList的方法：使用Collection.synchronizedList包装ArrayList
		@SuppressWarnings("unused")
		List<String> synchronizedList = Collections.synchronizedList(new ArrayList<String>());

		//另一个能实现同步的方式是使用Vector，但其性能太差，一般不用
		@SuppressWarnings("unused")
		Vector<String> vector = new Vector<>();
	}
}
