package concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 一种阻塞队列，其中每个插入操作必须等待另一个线程的对应移除操作 ，反之亦然。
 * 同步队列没有任何内部容量，甚至连一个队列的容量都没有。
 * 不能在同步队列上进行 peek，因为仅在试图要移除元素时，该元素才存在；
 * 除非另一个线程试图移除某个元素，否则也不能（使用任何方法）插入元素；
 * 也不能迭代队列，因为其中没有元素可用于迭代。
 * 队列的头 是尝试添加到队列中的首个已排队插入线程的元素；
 * 如果没有这样的已排队线程，则没有可用于移除的元素并且 poll() 将会返回 null。
 * 对于其他 Collection 方法（例如 contains），SynchronousQueue 作为一个空 collection。
 * 此队列不允许 null 元素。
 * 
 * @author Zhanghj
 *
 */
public class SynchronousQueueDemo {
	public static void main(String[] args) {
		SynchronousQueue<String> queue = new SynchronousQueue<String>();
		new Thread(new Producer(queue)).start();
		new Thread(new Customer(queue)).start();
	}
}

class Producer implements Runnable{
	
	private BlockingQueue<String> q;
	
	public Producer(BlockingQueue<String> q) {
		this.q = q;
	}
	
	@Override
	public void run() {
		List<String> list = Arrays.asList("one","two","three");
		try {
			for(int i = 0;i < 3;i++){
				q.put(list.get(i));
			}
			q.put("Done");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

class Customer implements Runnable{
	private BlockingQueue<String> q;
	
	public Customer(BlockingQueue<String> q) {
		super();
		this.q = q;
	}

	@Override
	public void run() {
		try {
			String obj = null;
			while (!("Done".equals(obj))) {
				obj = q.take();
				System.out.println(obj);
				Thread.sleep(3000);		//在不take时，producer不能put
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}