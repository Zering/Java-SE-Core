package algorithm4th.chapter2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 排序算法模板
 * @author Zhanghj @ 2016年10月25日
 *
 */
public class Example {

	public static void sort(Comparable[] a) {
		
	}
	
	//为了复用方法，将private改为protected，下同
	protected static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	
	protected static void exch(Comparable[] a, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	protected static void show(Comparable[] a) {
		//单行中打印数组
		for (int i = 0; i < a.length; i++) {
			StdOut.print(a[i] + " ");
		}
			StdOut.println();
	}
	
	public static boolean isSorted(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			if (less(a[i], a[i-1])) {
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		String[] a = In.readStrings();
		sort(a);
		assert isSorted(a);
		show(a);
	}
}
