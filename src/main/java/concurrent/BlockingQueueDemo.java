package concurrent;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 
		抛出异常		特殊值		阻塞		超时
	插入	add(e)		offer(e)	put(e)	offer(e, time, unit)
	移除	remove()	poll()		take()	poll(time, unit)
	检查	element()	peek()		不可用	不可用

 * 
 * 典型用例 生产者-消费者
 * @author Zhanghj
 *
 */
public class BlockingQueueDemo {
	public static void main(String[] args) {
		final BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
		final Random random = new Random();
		
		class Producer implements Runnable{

			@Override
			public void run() {
				while (true) {
					int i = random.nextInt(10);
					try {
						queue.put(i);
						if (queue.size() == 3) {
							System.out.println("队列已满。");
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		}
		
		class Customer implements Runnable{

			@Override
			public void run() {
				while (true) {
					try {
						int used = queue.take();
						System.out.println(Thread.currentThread().getName()+" Customer used:"+used);
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
		
		//创建一个生产者
		new Thread(new Producer()).start();
		
		//创建一百个消费者
		for (int i = 0; i < 100; i++){
			new Thread(new Customer()).start();
		}
	}
}
