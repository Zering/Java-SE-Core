package concurrent;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
/**
 * Semaphore 一个计数信号量。从概念上讲，信号量维护了一个许可集。
 * 如有必要，在许可可用前会阻塞每一个 acquire()，然后再获取该许可。
 * 每个 release() 添加一个许可，从而可能释放一个正在阻塞的获取者。
 * 但是，不使用实际的许可对象，Semaphore 只对可用许可的号码进行计数，并采取相应的行动。
 * Semaphore 通常用于限制可以访问某些资源（物理或逻辑的）的线程数目。
 * @author Zhanghj
 *
 */
public class SemaphoreDemo {

	public static void main(String[] args) {
		SemaphoreDemo demo = new SemaphoreDemo();
		//信号量控制为两个
		final BoundedHashSet<String> set = demo.getSet(2);
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		//第三个线程被限制,在release一个元素之后才能加入第三个元素
		for(int i = 0; i < 3;i++){
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					try {
						set.add(Thread.currentThread().getName());
						Thread.sleep(1000);
						set.remove(Thread.currentThread().getName());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		executorService.shutdown();
		
	}
	
	public BoundedHashSet<String> getSet(int bound) {
		return new BoundedHashSet<String>(bound);
	}
}

class BoundedHashSet<T>{
	private final Set<T> set;
	private final Semaphore semaphore;
	public BoundedHashSet(int bound) {
		this.set = Collections.synchronizedSet(new HashSet<T>());
		this.semaphore = new Semaphore(bound, true);
	}
	
	public void add(T o) throws InterruptedException {
		semaphore.acquire();
		set.add(o);
		System.out.printf("add: %s%n",o);
	}
	
	public void remove(T o) {
		if(set.remove(o)){
			semaphore.release();
			System.out.printf("remove: %s%n",o);
		}else {
			System.out.printf("not found: %s%n",o);
		}
	}
}

