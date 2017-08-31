package collection.queue;

import java.util.PriorityQueue;

public class PriorityQueueDemo {

	public static void main(String[] args) {
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		
		queue.add(65);
		queue.add(384);
		queue.add(12);
		queue.add(16);
		queue.add(53);
		queue.add(31);
		queue.add(76);
		queue.add(87);
	
		while(!queue.isEmpty()){
			System.out.println(queue.remove());
		}
	}
}
