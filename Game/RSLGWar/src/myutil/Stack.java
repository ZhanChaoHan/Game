package myutil;

import java.util.LinkedList;

public class Stack extends LinkedList {

	public void push(Object e) {
		this.addFirst(e);
	}

	public Object pop() {
		return this.poll();
	}
}
