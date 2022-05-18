package game;

public class ChangeTracker {

	private Object previous = null;

	public ChangeTracker() {
	}

	public ChangeTracker(Object e) {
		this.previous = e;
	}

	public boolean isChanged(Object e) {
		boolean b;
		if (this.previous != null && e != null) {
			b = !this.previous.equals(e);
		} else {
			b = this.previous != e;
		}

		this.previous = e;
		return b;
	}
}
