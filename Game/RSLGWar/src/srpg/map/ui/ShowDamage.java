// Decompiled by:       Fernflower v0.6
// Date:                24.10.2010 08:56:27
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package srpg.map.ui;

import game.Draw;
import game.Plane;
import game.Sprite;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import srpg.Damage;
import srpg.SRPGGameObjectManager;
import srpg.map.MapUserIF;
import srpg.map.StageDrawOrder;
import srpg.map.StageMap;

public class ShowDamage extends MapUserIF {

	int damage;
	String str = "";
	boolean hit;
	Color c;
	double topX;
	double topX2;
	double topY;
	Random rn;
	int count = 0;
	int countMax = 10;

	public ShowDamage(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
			int y, StageMap m) {
		super(gom, sp, plnNo, x, y, m);
		sp.setPos(plnNo, StageDrawOrder.EFFECT.z());
		sp.setPos(plnNo, -100.0D, -100.0D);
		sp.setDraw(plnNo, new Draw() {
			public void drawing(Graphics g, Plane pln) {
				g.setColor(ShowDamage.this.c);
				Sprite.drawShadedString(ShowDamage.this.str,
						((Double) pln.coord.x).intValue(),
						((Double) pln.coord.y).intValue(), Color.DARK_GRAY, g);
			}
		});
		this.rn = new Random();
		double var10001 = this.rn.nextDouble() - 0.5D;
		this.map.getClass();
		this.topX = var10001 * 48.0D / 2.0D;
		if (Math.abs(this.topX) < 0.1D) {
			this.topX = Math.signum(this.topX) * 0.1D;
		}

		this.topX2 = Math.pow(this.topX, 2.0D);
		this.topY = (this.rn.nextDouble() * 2.0D / 3.0D + 0.3333333432674408D)
				* (double) this.map.hexHeight * 4.0D;
	}

	public void destructor() {
		this.map.unitMap[this.y][this.x].damaging();
		super.destructor();
	}

	public void setDamage(Damage d, boolean hit) {
		this.hit = hit;
		if (!hit) {
			this.c = Color.WHITE;
			this.str = this.str + "miss";
			this.damage = -1;
			this.sp.playSE("fin");
		} else {
			if (d.hp <= 0 && d.sp <= 0) {
				if (d.hp < 0 || d.sp < 0) {
					this.sp.playSE("power35");
				}
			} else {
				this.sp.playSE("bashi2");
			}

			if (d.sp != 0) {
				this.c = new Color(120, 180, 240);
				this.str = this.str + Math.abs(d.sp);
				this.damage = d.sp;
			} else if (d.hp >= 0) {
				this.c = Color.WHITE;
				this.str = this.str + d.hp;
				this.damage = d.hp;
			} else {
				this.c = new Color(110, 210, 140);
				this.str = this.str + -d.hp;
				this.damage = d.hp;
			}

		}
	}

	public void action() {
		this.sp.setPos(this.plnNo,
				this.map.getHexCoord((double) this.x, (double) this.y));
		Sprite var10000 = this.sp;
		int var10001 = this.plnNo;
		this.map.getClass();
		var10000.setMov(var10001, (double) (48 / 2 - this.str.length() * 3),
				(double) (this.map.hexH / 2 - this.map.hexHeight * 2));
		double x;
		double y;
		if (this.damage >= 0) {
			x = this.topX * 5.0D / 2.0D * (double) this.count
					/ (double) this.countMax;
			y = this.topY / this.topX2 * Math.pow(x - this.topX, 2.0D)
					- this.topY;
		} else {
			x = 0.0D;
			y = (double) (-this.map.hexHeight) * 3.5D * (double) this.count
					/ (double) this.countMax;
		}

		this.sp.setMov(this.plnNo, x, y);
		++this.count;
		if (this.count > this.countMax) {
			this.destructor();
		}

	}
}
