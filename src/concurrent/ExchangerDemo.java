package concurrent;

import java.util.ArrayList;
import java.util.concurrent.Exchanger;

/**
 * 可以在对中对元素进行配对和交换的线程的同步点。
 * 每个线程将条目上的某个方法呈现给 exchange 方法，与伙伴线程进行匹配，并且在返回时接收其伙伴的对象。
 * Exchanger 可能被视为 SynchronousQueue 的双向形式。Exchanger 可能在应用程序（比如遗传算法和管道设计）中很有用。
 * 
 * 示例：使用 Exchanger 在线程间交换缓冲区，因此，在需要时，填充缓冲区的线程获取一个新腾空的缓冲区，并将填满的缓冲区传递给腾空缓冲区的线程。
 * @author Zhanghj
 *
 */
public class ExchangerDemo {
	
	public static void main(String[] args) {
		final Exchanger<ArrayList<Integer>> exchanger = new Exchanger<ArrayList<Integer>>();
		final ArrayList<Integer> buff1 = new ArrayList<Integer>(10);
		final ArrayList<Integer> buff2 = new ArrayList<Integer>(10);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				ArrayList<Integer> buff = buff1;
				while(true){
					

					if (buff.size() >= 10) {
						try {
							System.out.println("before exchange buff1.size():"+buff.size());
							buff = exchanger.exchange(buff);
							System.out.println("Exchange buff1");
							System.out.println("after exchange buff1.size():"+buff.size());
//							for (Integer integer : buff) {
//								System.out.println(Thread.currentThread().getName()+": "+integer);
//							}
							buff.clear();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					buff.add((int)(Math.random()*1000));
					try {
						Thread.sleep((long)(Math.random()*1000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				ArrayList<Integer> buff = buff2;
				while (true) {
					System.out.println("buff2.size():"+buff.size());
//					for (Integer integer : buff) {
//						System.out.println(Thread.currentThread().getName()+": "+integer);
//					}
					
					try {
						Thread.sleep(1000);
						buff = exchanger.exchange(buff);
						System.out.println("Exchange buff2");
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}
