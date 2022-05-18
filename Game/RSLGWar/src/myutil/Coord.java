package myutil;

public class Coord implements Cloneable {

	public Number x;
	public Number y;

	public Coord(Number x, Number y) {
		this.x = x;
		this.y = y;
	}

	public void assign(Coord co) {
		this.x = co.x;
		this.y = co.y;
	}

	public Coord clone() {
		return new Coord(this.x, this.y);
	}

	public boolean equals(Object o) {
		Coord co = (Coord) o;
		return co.x == this.x && co.y == this.y;
	}

	public int hashCode() {
		return (int) (this.x.doubleValue() * 65535.0D + this.y.doubleValue());
	}

	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}
}
