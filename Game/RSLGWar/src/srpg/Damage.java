package srpg;

public class Damage {

	public static final Damage ZERO = new Damage(0, 0, 0, 0.0D);
	public final int hp;
	public final int sp;
	final int res;
	public final double hit;

	Damage(int hp, int sp, int res, double hit) {
		this.hp = hp;
		this.sp = sp;
		this.res = res;
		this.hit = hit;
	}

	public Damage effect(double e) {
		return new Damage((int) ((double) this.hp * e),
				(int) ((double) this.sp * e), (int) ((double) this.res * e),
				this.hit);
	}

	public Damage hitEffect(double e) {
		return new Damage(this.hp, this.sp, this.res, this.hit * e);
	}
}
