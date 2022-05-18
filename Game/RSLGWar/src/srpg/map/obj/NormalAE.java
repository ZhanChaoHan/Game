// Decompiled by:       Fernflower v0.6
// Date:                24.10.2010 08:56:16
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package srpg.map.obj;

import game.Sprite;
import myutil.Coord;
import srpg.SRPGGameObjectManager;
import srpg.map.StageMap;
import srpg.map.obj.AttackEffect;

public class NormalAE extends AttackEffect {

	private Coord before;
	private Coord after;
	private double dir;
	private double cos;
	private double sin;
	private int offX;
	private int offY;
	private int speed;

	public NormalAE(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
			int y, StageMap m) {
		super(gom, sp, plnNo, x, y, m);
		this.initBullet("tama3", 3, 15, "tm2r_gun18_d");
	}

	public void action() {
		if (this.bulletAction()) {
			this.destructor();
			this.damage();
		}

	}

	protected void initBullet(String file, int trail, int speed, String se) {
		this.sp.addGrp(file);
		this.sp.setImg(this.plnNo, file);
		this.sp.setTrailCount(this.plnNo, trail);
		this.before = this.map.getHexCoord(this.map.current.getPos());
		this.setHexCenter(this.before);
		this.sp.setPos(this.plnNo, this.before);
		Coord size = this.sp.getImgSize(this.plnNo);
		this.offX = -((Integer) size.x).intValue() / 2;
		this.offY = -((Integer) size.y).intValue() / 2 - this.map.hexH / 2;
		this.sp.setMov(this.plnNo, (double) this.offX, (double) this.offY);
		this.dir = Math.atan2(((Double) this.target.y).doubleValue()
				- ((Double) this.before.y).doubleValue(),
				((Double) this.target.x).doubleValue()
						- ((Double) this.before.x).doubleValue());
		this.cos = Math.cos(this.dir);
		this.sin = Math.sin(this.dir);
		this.current = this.sp.getPos(this.plnNo);
		this.speed = speed;
		this.sp.playSE(se);
	}

	protected boolean bulletAction() {
		if (this.sp.isAnimeClosed(this.plnNo)) {
			return true;
		} else {
			this.sp.setMov(this.plnNo, (double) (-this.offX),
					(double) (-this.offY));
			this.target = this.map
					.getHexCoord((double) this.x, (double) this.y);
			this.setHexCenter(this.target);
			this.after = this.map
					.getHexCoord(
							(double) ((Integer) this.map.current.getPos().x)
									.intValue(),
							(double) ((Integer) this.map.current.getPos().y)
									.intValue());
			this.setHexCenter(this.after);
			Coord difference = new Coord(Double.valueOf(((Double) this.after.x)
					.doubleValue() - ((Double) this.before.x).doubleValue()),
					Double.valueOf(((Double) this.after.y).doubleValue()
							- ((Double) this.before.y).doubleValue()));
			this.sp.setMov(this.plnNo, ((Double) difference.x).doubleValue()
					+ (double) this.speed * this.cos,
					((Double) difference.y).doubleValue() + (double) this.speed
							* this.sin);
			this.before.assign(this.after);
			this.current = this.sp.getPos(this.plnNo);
			if (this.cos > 0.0D
					&& ((Double) this.current.x).doubleValue() > ((Double) this.target.x)
							.doubleValue()
					|| this.cos < 0.0D
					&& ((Double) this.current.x).doubleValue() < ((Double) this.target.x)
							.doubleValue()
					|| this.sin > 0.0D
					&& ((Double) this.current.y).doubleValue() > ((Double) this.target.y)
							.doubleValue()
					|| this.sin < 0.0D
					&& ((Double) this.current.y).doubleValue() < ((Double) this.target.y)
							.doubleValue()) {
				this.sp.setImg(this.plnNo, (String) null);
			}

			this.sp.setMov(this.plnNo, (double) this.offX, (double) this.offY);
			return false;
		}
	}
}
