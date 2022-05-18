// Decompiled by:       Fernflower v0.6
// Date:                24.10.2010 08:56:16
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package srpg.map.obj;

import game.Motionable;
import game.Sprite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import myutil.Coord;
import srpg.SRPGGameObjectManager;
import srpg.map.MapDrawOrder;
import srpg.map.MapObject;
import srpg.map.StageMap;

public class Obstacle extends MapObject implements Motionable {

	final int height;
	BufferedImage img;

	public Obstacle(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
			int y, StageMap m) {
		super(gom, sp, plnNo, x, y, m);
		String str = "tree";
		sp.addGrp(str);
		this.img = sp.getGrp(str);
		this.initImg();
		this.drawImg();
		sp.setPos(plnNo, this.map.getHexCoord((double) x, (double) y));
		sp.setPos(plnNo, MapDrawOrder.UNIT.z() + (double) y
				* 0.00392156862745098D);
		this.height = this.map.hexH / 3;
		gom.addMotionable(this);
	}

	public void destructor() {
		this.gom.removeMotionable(this);
		super.destructor();
	}

	public void motion() {
		Coord c = this.map.getHexCoord((double) this.x, (double) this.y);
		this.sp.setPos(
				this.plnNo,
				(double) ((Double) c.x).intValue(),
				(double) (((Double) c.y).intValue() - this.map.hexH * 2 / 3 - 3));
	}

	public int processPriority() {
		return 0;
	}

	private void initImg() {
		Sprite var10000 = this.sp;
		int var10001 = this.plnNo;
		this.map.getClass();
		var10000.initImg(var10001, 48, this.map.hexH * 2);
	}

	private void drawImg() {
		Graphics2D g = this.sp.createGraphics(this.plnNo);
		g.drawImage(this.img, 0, 0, (ImageObserver) null);
		g.dispose();
	}
}
