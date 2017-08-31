package concurrent;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledThreadPoolExecutor，它可另行安排在给定的延迟后运行命令，或者定期执行命令。
 * 需要多个辅助线程时，或者要求 ThreadPoolExecutor 具有额外的灵活性或功能时，此类要优于 Timer。
 * 一旦启用已延迟的任务就执行它，但是有关何时启用，启用后何时执行则没有任何实时保证。
 * 按照提交的先进先出 (FIFO) 顺序来启用那些被安排在同一执行时间的任务。
 * 
 * @author Zhanghj
 */
public class ScheduledThreadPoolExecutorDemo {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);

        scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> System.out.println("1s later start execute,and execute once per 5s"), 1, 5,
                TimeUnit.SECONDS);

        scheduledThreadPoolExecutor.scheduleAtFixedRate(() -> System.out.println("2s later start execute,and execute once per 2s"), 2, 2,
                TimeUnit.SECONDS);
        //两个定时任务互不干扰
    }
}
