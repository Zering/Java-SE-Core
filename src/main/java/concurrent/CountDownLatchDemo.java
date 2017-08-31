package concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 是一个通用同步工具，它有很多用途。将计数 1 初始化的 CountDownLatch 用作一个简单的开/关锁存器，或入口：
 * 在通过调用 countDown() 的线程打开入口前，所有调用 await 的线程都一直在入口处等待。
 * 用 N 初始化的 CountDownLatch 可以使一个线程在 N 个线程完成某项操作之前一直等待，或者使其在某项操作完成 N 次之前一直等待。
 * @author Zhanghj
 *
 */
public class CountDownLatchDemo {
	private static final int N = 10;
	public static void main(String[] args) {
		CountDownLatch doneSignal = new CountDownLatch(N);
		CountDownLatch startSignal = new CountDownLatch(1);
		
		for (int i = 1; i <= N; i++) {  
            new Thread(new Worker(startSignal,doneSignal,i)).start();//线程启动了  
        }  
        System.out.println("begin------------");  
        startSignal.countDown();//开始执行啦  
        try {
			doneSignal.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}//等待所有的线程执行完毕  
        System.out.println("Ok");  
		
	}
}

class Worker implements Runnable{
	
	private final CountDownLatch startSignal;
	private final CountDownLatch doneSignal;
	private int beginIndex;
	
	public Worker(CountDownLatch startSignal, CountDownLatch doneSignal, int beginIndex) {
		super();
		this.startSignal = startSignal;
		this.doneSignal = doneSignal;
		this.beginIndex = beginIndex;
	}

	@Override
	public void run() {
		System.out.println("Thread:"+beginIndex+" start;");
		try {
			startSignal.await();
			int count = (beginIndex - 1) * 10 + 1;  
            for (int i = count; i <= count + 10; i++) {  
                System.out.println(i);  
            }  
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			doneSignal.countDown();
		}
		
		System.out.println("Thread:"+beginIndex+" end;");
	}
	
}