// Decompiled by:       Fernflower v0.6
// Date:                24.10.2010 08:56:15
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package srpg.map.obj;

import game.Draw;
import game.Motionable;
import game.Plane;
import game.Sprite;
import java.awt.Color;
import java.awt.Graphics;
import srpg.SRPGGameObjectManager;
import srpg.UnitStatus;
import srpg.map.MapDrawOrder;
import srpg.map.MapObject;
import srpg.map.StageMap;
import srpg.map.obj.Unit;

public class Gage extends MapObject implements Motionable {

	Unit unit;
	Color hpc = new Color(255, 170, 136, 204);
	Color spc = new Color(136, 170, 255, 204);
	Color tpc = new Color(170, 255, 136, 204);
	Color tpMaxc = new Color(221, 255, 136, 204);

	public Gage(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x, int y,
			StageMap m) {
		super(gom, sp, plnNo, x, y, m);
		sp.setDraw(plnNo, new Draw() {
			public void drawing(Graphics g, Plane pln) {
				Gage.this.drawGages(g, ((Double) pln.coord.x).doubleValue(),
						((Double) pln.coord.y).doubleValue());
			}
		});
		this.unit = this.map.unitMap[y][x];
		gom.addMotionable(this);
	}

	public void destructor() {
		this.gom.removeMotionable(this);
		super.destructor();
	}

	public void motion() {
		this.sp.setPos(this.plnNo, this.map.getHexCoord(this.unit.getPos()));
		this.sp.setMov(this.plnNo, 0.0D, (double) (-this.map.hexH * 3 / 4));
		this.sp.setPos(this.plnNo, MapDrawOrder.UNIT.z()
				+ (double) ((Integer) this.unit.getPos().y).intValue()
				* 0.00392156862745098D);
	}

	public int processPriority() {
		return 0;
	}

	protected void drawGages(Graphics g, double x, double y) {
		byte h = 4;
		UnitStatus status = this.unit.getStatus();
		this.drawGage(g, x, y, h, this.hpc, status.maxHP, status.hp);
		this.drawGage(g, x, y + (double) h, h, this.spc, status.maxSP,
				status.sp);
		this.drawGage(g, x, y + (double) (h * 2), h,
				status.tension < 1.0D ? this.tpc : this.tpMaxc, 100,
				(int) (status.tension * 100.0D));
	}

	protected void drawGage(Graphics g, double x, double y, int height,
			Color c, int max, int p) {
		g.setColor(c);
		this.map.getClass();
		int width = 48 - 4;
		int posX = (int) x + 2;
		int posY = (int) y - this.map.hexH * 2 + this.map.hexH + 14;
		g.fillRect(posX, posY, width * p / max, height);
		g.setColor(Color.BLACK);
		g.drawRect(posX, posY, width, height);
	}
}
