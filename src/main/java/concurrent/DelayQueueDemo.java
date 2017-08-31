package concurrent;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 只有在延迟期满时才能从中提取元素。
 * 该队列的头部 是延迟期满后保存时间最长的 Delayed 元素。
 * 如果延迟都还没有期满，则队列没有头部，并且 poll 将返回 null。
 * 当一个元素的 getDelay(TimeUnit.NANOSECONDS) 方法返回一个小于等于 0 的值时，将发生到期。
 * 即使无法使用 take 或 poll 移除未到期的元素，也不会将这些元素作为正常元素对待。
 * 例如，size 方法同时返回到期和未到期元素的计数。
 * 此队列不允许使用 null 元素。
 * 
 * @author Zhanghj
 */
public class DelayQueueDemo {

    class Stadium implements Delayed {

        long trigger;

        public Stadium(long i) {
            trigger = System.currentTimeMillis() + i;
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return trigger - System.currentTimeMillis();
        }

    }

    public static void main(String[] args) throws Exception {
        DelayQueue<Stadium> delayQueue = new DelayQueue<>();

        for (int i = 0; i < 5; i++) {
            delayQueue.add(new DelayQueueDemo().new Stadium(new Random().nextInt(30000)));
        }
        Thread.sleep(1000);
        while (true) {
            if (delayQueue.size() == 0)
                break;
            Stadium stadium = delayQueue.take();
            if (stadium != null) {
                System.out.println("stadium: " + stadium);
                System.out.println("与当前时间的差时：" + (System.currentTimeMillis() - stadium.trigger));
            }
        }

    }
}
