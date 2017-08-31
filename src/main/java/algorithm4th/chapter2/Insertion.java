package algorithm4th.chapter2;
/**
 * 插入排序
 * @author Zhanghj @ 2016年10月25日
 *
 */
public class Insertion extends Example{
	
	public static void sort(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
				exch(a, j, j-1);
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
