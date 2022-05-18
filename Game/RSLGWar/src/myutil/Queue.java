package myutil;

import java.util.Iterator;
import java.util.LinkedList;

public class Queue extends LinkedList {

	public Object enqueue(Object element) {
		this.add(element);
		return element;
	}

	public Queue enqueue(Queue q) {
		Iterator var3 = q.iterator();

		while (var3.hasNext()) {
			Object e = (Object) var3.next();
			this.add(e);
		}

		return q;
	}

	public Object dequeue() {
		return this.poll();
	}
}
