// Decompiled by:       Fernflower v0.6
// Date:                24.10.2010 08:56:27
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package srpg.map.ui;

import game.Sprite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import srpg.SRPGGameObjectManager;
import srpg.map.MapObject;
import srpg.map.StageDrawOrder;
import srpg.map.StageMap;
import srpg.map.obj.Unit;

public class TurnInfo extends MapObject {

	private int width;
	private int height;
	private int elementCount;
	private Unit[] units;
	BufferedImage bgImg;
	double eachHeight;

	public TurnInfo(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
			int y, StageMap m) {
		super(gom, sp, plnNo, x, y, m);
		byte topOffset = 90;
		byte bottomOffset = 75;
		this.width = 120;
		this.height = gom.height - topOffset - bottomOffset;
		sp.setPos(plnNo, (double) (gom.width - this.width), (double) topOffset);
		sp.setPos(plnNo, StageDrawOrder.INFO.z());
	}

	private void initImg() {
		this.sp.initImg(this.plnNo, this.width, this.height);
		this.eachHeight = (double) this.height / (double) this.elementCount;
		this.bgImg = new BufferedImage(this.width, this.height, 2);
		Graphics2D gbg = this.bgImg.createGraphics();
		Sprite.setTransparent(this.bgImg);
		gbg.setColor(new Color(1722460927, true));
		gbg.fillRect(0, 0, this.width, this.height);

		for (int i = 0; i < this.elementCount; ++i) {
			int rectY = (int) ((double) i * this.eachHeight);
			gbg.setColor(new Color(1715749956, true));
			gbg.drawRect(0, rectY, this.width, (int) this.eachHeight);
		}

		gbg.dispose();
	}

	private void drawImg() {
		this.sp.setTransparent(this.plnNo);
		Graphics2D g = this.sp.createGraphics(this.plnNo);
		g.drawImage(this.bgImg, 0, 0, (ImageObserver) null);
		byte offsetX = 14;

		int i;
		int rectY;
		for (i = 0; i < this.elementCount; ++i) {
			rectY = (int) ((double) i * this.eachHeight);
			byte x = 0;
			int y = rectY;
			int var10000 = this.units[i].face.getWidth();
			this.map.getClass();
			if (var10000 <= 48 + 5) {
				x = offsetX;
				y = rectY - (this.map.hexH / 2 - 5);
			}

			g.setClip(x, rectY, this.width, (int) this.eachHeight);
			g.drawImage(this.units[i].face, x, y, (ImageObserver) null);
		}

		g.setClip(0, 0, this.width, this.height);
		g.setColor(new Color(255, 255, 255, 255));
		g.drawRect(0, 0, this.width, (int) this.eachHeight);
		g.setColor(new Color(1157627903, true));
		g.fillRect(0, (int) this.eachHeight, this.width, this.height
				- (int) this.eachHeight);
		g.setColor(Color.BLACK);

		for (i = 0; i < this.elementCount; ++i) {
			rectY = (int) ((double) i * this.eachHeight);
			Sprite.drawShadedString(this.units[i].getName(), 15, rectY
					+ (int) this.eachHeight - 5, g);
		}

		g.setColor(Color.BLACK);
		g.dispose();
	}

	public void setUnits(Unit[] units) {
		this.units = units;
		this.drawImg();
	}

	public void setElementCount(int ec) {
		this.elementCount = ec;
		this.initImg();
	}
}
