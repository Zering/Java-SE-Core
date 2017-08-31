package concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 可取消的异步计算。利用开始和取消计算的方法、查询计算是否完成的方法和获取计算结果的方法，
 * 此类提供了对 Future 的基本实现。仅在计算完成时才能获取结果；
 * 如果计算尚未完成，则阻塞 get 方法。一旦计算完成，就不能再重新开始或取消计算。
 * @author Zhanghj
 *
 */
public class FutureTaskDemo {

	public static void main(String[] args) {
		FutureTask<String> task = new FutureTask<>(() -> {
            //do something which needs long time
            return Thread.currentThread().getName();
        });
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(task);
		try {
			String result = task.get();
			System.out.println(result);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

	}
}
