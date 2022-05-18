package game;

public class Blink {

	private int count = 0;
	private int span;
	private int time;

	public Blink(int max, int time) {
		this.span = max / time;
		this.time = time;
	}

	public int blinking() {
		this.count %= this.time;
		++this.count;
		return -this.count * this.span;
	}
}
