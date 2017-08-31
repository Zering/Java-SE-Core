package algorithm4th.chapter2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 选择排序
 * @author Zhanghj @ 2016年10月25日
 *
 */
public class Selection extends Example{

	public static void sort(Comparable[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			int min = i;
			for (int j = i+1; j < N; j++) {
				if (less(a[j],a[min])) {
					min = j;
				}
				exch(a, i, min);
			}
		}
	}
	
	public static void main(String[] args) {
		String[] a = new String[]{"fe","hba","aw","ht","yjt","vecf"};
		sort(a);
		assert isSorted(a);
		show(a);
	}
	
}
