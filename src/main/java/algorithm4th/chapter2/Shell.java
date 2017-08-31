package algorithm4th.chapter2;
/**
 * 希尔排序
 * @author Zhanghj @ 2016年10月25日
 *
 */
public class Shell extends Example{
	
	public static void sort(Comparable[] a) {
		int N = a.length;
		int h = 1;
		while(h < N/3)
			h = 3*h +1;
		while (h >=1) {
			for (int i = h; i < N; i++) {
				for(int j = i; j > 0 && less(a[j], a[j - h]); j-=h){
					exch(a, j, j-h);
				}
			}
			h = h/3;
		}
	}
	
	public static void main(String[] args) {
		String[] a = new String[]{"fe","hba","aw","ht","yjt","vecf"};
		sort(a);
		assert isSorted(a);
		show(a);
	}
}
