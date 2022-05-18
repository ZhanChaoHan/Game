// Decompiled by:       Fernflower v0.6
// Date:                24.10.2010 08:56:14
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package srpg.map;

import game.Motionable;
import game.Sprite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import myutil.Coord;
import srpg.SRPGGameObjectManager;
import srpg.map.MapObject;
import srpg.map.StageMap;

public abstract class HorizonHex extends MapObject implements Motionable {

	private Coord maxHex;
	private int maxHeight = Integer.MIN_VALUE;
	private int minHeight = Integer.MAX_VALUE;
	protected Coord offset;
	private int startX = 0;
	private int endX;
	private int leftX;
	private int rightX;
	private boolean[] drawArea;
	protected int bottomOffset;
	protected boolean repaint;

	public HorizonHex(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
			int hy, StageMap m) {
		super(gom, sp, plnNo, x, hy, m);
		this.endX = this.map.hex[this.y].length;
		this.leftX = 0;
		this.rightX = 0;
		this.bottomOffset = 0;
		this.repaint = true;
		this.init();
		this.initImg();
		this.motion();
		gom.addMotionable(this);
	}

	public void destructor() {
		this.gom.removeMotionable(this);
		super.destructor();
	}

	public void motion() {
		this.sp.setPos(this.plnNo, this.map.getHexCoord(0.0D, 0.0D));
		this.sp.setMov(
				this.plnNo,
				((Double) this.offset.x).doubleValue(),
				((Double) this.offset.y).doubleValue()
						+ (double) (this.map.hex[0][0].height * this.map.hexHeight));
		if (this.repaint) {
			this.drawImg();
			this.repaint = false;
		}

	}

	public int processPriority() {
		return 0;
	}

	private void init() {
		if (this.drawArea != null) {
			this.startX = this.leftX;
			this.endX = this.rightX;
		}

		int hx;
		for (hx = this.startX; hx < this.endX; ++hx) {
			if (this.maxHeight < this.map.hex[this.y][hx].height
					&& (this.drawArea == null || this.drawArea[hx])) {
				this.maxHeight = this.map.hex[this.y][hx].height;
				this.maxHex = new Coord(Integer.valueOf(hx),
						Integer.valueOf(this.y));
			}
		}

		for (hx = this.startX; hx < this.endX; ++hx) {
			if (this.minHeight > this.map.hex[this.y][hx].height
					&& (this.drawArea == null || this.drawArea[hx])) {
				this.minHeight = this.map.hex[this.y][hx].height;
			}
		}

		this.offset = new Coord((Double) this.map.getHexCoord(
				(double) this.leftX, 0.0D, false).x,
				(Double) this.map.getHexCoord(this.maxHex, false).y);
	}

	void setDrawArea(boolean[] da) {
		if (da.length != this.map.hex.length) {
			System.out.println("HrizonHexError : illegal format drawArea");
		} else {
			this.drawArea = da;

			int i;
			for (i = 0; i < da.length; ++i) {
				if (da[i]) {
					this.leftX = i;
				}
			}

			for (i = da.length - 1; i >= 0; --i) {
				if (da[i]) {
					this.rightX = i + 1;
				}
			}

			if (this.leftX - this.rightX <= 0) {
				this.sp.setView(this.plnNo, false);
			}

			this.init();
		}
	}

	private void initImg() {
		this.map.getClass();
		int var10000 = 48 * (this.endX - this.startX);
		this.map.getClass();
		int width = var10000 + 48 / 2 + 1;
		int height = this.map.hexH * 2 + this.map.hexHeight
				* (this.maxHeight - this.minHeight) + this.bottomOffset;
		this.sp.initImgs(this.plnNo, 1, width, height);
	}

	private void drawImg() {
		this.sp.setTransparent(this.plnNo);
		Graphics2D g = this.sp.createGraphics(this.plnNo);

		for (int x = this.startX; x < this.endX; ++x) {
			if (this.drawArea == null || this.drawArea[x]) {
				this.drawEachHex(g, x);
			}
		}

		g.dispose();
	}

	protected abstract void drawEachHex(Graphics var1, int var2);
}
