package concurrent.excutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhanghaojie on 2017/12/10.
 *
 * jdk提供的线程池管理类实例
 */
public class ExecutorDemo {

  private static final int THREAD_MAX_NUM = 10;

  public static void main(String[] args) throws InterruptedException {
    // 线程数固定的线程池
    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(THREAD_MAX_NUM);
    // 线程池的大小为10，如果放入超过最大线程数的任务，则后续任务会等待空闲线程再开始执行
    for (int i = 0; i < THREAD_MAX_NUM * 2; i++) {
      fixedThreadPool.execute(new Worker());
    }
    // fixedThreadPool即使在没有任何任务的时候，也保持线程存活,以减少线程创建和销毁的开销
    System.out.println("当前存活的线程数量1：" + Thread.activeCount());
    // 调用shutdown以关闭线程池（shutdown会等待所有已提交的任务完成）
    fixedThreadPool.shutdown();
    // 等待关闭
    while (!fixedThreadPool.awaitTermination(10, TimeUnit.SECONDS)) {
      ;
    }
    System.out.println("当前存活的线程数量2：" + Thread.activeCount());
    System.out.println("-----fixedThreadPool End-----");
    ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    for (int i = 0; i < THREAD_MAX_NUM * 2; i++) {
      cachedThreadPool.execute(new Worker());
    }
    // cachedThreadPool如果当前没有线程执行一个新的任务时自动的开辟一个新的线程
    System.out.println("当前存活的线程数量3：" + Thread.activeCount());
    // cachedThreadPool在线程空闲时回收线程
    Thread.currentThread().sleep(5000);
    System.out.println("当前存活的线程数量4：" + Thread.activeCount());
    // cachedThreadPool的问题在于在高并发的环境下，开辟过多的线程会暂用大量的系统资源和内存，甚至导致OOM
    cachedThreadPool.shutdown();
    while (!(cachedThreadPool.awaitTermination(10, TimeUnit.SECONDS))) {
      ;
    }
    System.out.println("-----cachedThreadPool End------");
    // 单线程线程池，一次只处理一个任务，用这个可以方便生命周期的管理
    ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
    for (int i = 0; i < THREAD_MAX_NUM * 2; i++) {
      singleThreadExecutor.execute(new Worker());
    }
    System.out.println("当前存活的线程数量5：" + Thread.activeCount());
    singleThreadExecutor.shutdown();
    while (!singleThreadExecutor.awaitTermination(10, TimeUnit.SECONDS)) {
      ;
    }
    System.out.println("-----singleThreadExecutor End-----");
    // 定长的延时或定时任务的线程池管理器
    ScheduledExecutorService scheduledExecutorService = Executors
        .newScheduledThreadPool(THREAD_MAX_NUM);
    // 1s后执行一个worker
    scheduledExecutorService.schedule(new Worker(), 1, TimeUnit.SECONDS);
    // 1s后执行一个worker，此后每隔2s执行一次worker(如果两秒时上一个任务还没有完成会在上一个任务完成时立即执行并重新计时)
    scheduledExecutorService.scheduleAtFixedRate(new Worker(), 1, 2, TimeUnit.SECONDS);
    // 1s后执行一个worker, 此后在当前任务完成后延迟2s再次执行，以此循环
    scheduledExecutorService.scheduleWithFixedDelay(new Worker(), 1, 2, TimeUnit.SECONDS);
    Thread.sleep(10000);
    scheduledExecutorService.shutdown();

  }


}

class Worker implements Runnable {

  @Override
  public void run() {
    System.out
        .println(System.currentTimeMillis() + "   " + Thread.currentThread().getName() + "start");
    try {
      // 假定完成一个任务需要10s
      Thread.currentThread().sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out
        .println(System.currentTimeMillis() + "   " + Thread.currentThread().getName() + "end");
  }
}
