// Decompiled by:       Fernflower v0.6
// Date:                24.10.2010 08:56:16
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package srpg.map.obj;

import game.Sprite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import myutil.Coord;
import srpg.SRPGGameObjectManager;
import srpg.map.HorizonHex;
import srpg.map.MapDrawOrder;
import srpg.map.StageMap;

public class HorizonMap extends HorizonHex {

	static final int stageHeight = 4;
	int fontSize = 16;

	public HorizonMap(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
			int hy, StageMap m) {
		super(gom, sp, plnNo, x, hy, m);
		this.bottomOffset = this.map.hexHeight * 2;
		sp.setPos(plnNo, MapDrawOrder.MAP.z() + (double) this.y
				* 0.00392156862745098D);
	}

	protected void drawEachHex(Graphics g, int x) {
		Coord hc = this.map.getHexCoord((double) x, (double) this.y, false);
		int hy = (int) (((Double) hc.y).doubleValue() - ((Double) this.offset.y)
				.doubleValue());
		g.setColor(new Color(7838054));
		int basicHeight = this.map.hex[this.y][x].height + 4;
		int heightDifL;
		int heightDifR;
		if (this.y + 1 < this.map.hex.length) {
			if (this.y % 2 == 0) {
				heightDifL = x - 1 >= 0 ? this.map.hex[this.y][x].height
						- this.map.hex[this.y + 1][x - 1].height : basicHeight;
				heightDifR = x < this.map.hex[this.y + 1].length ? this.map.hex[this.y][x].height
						- this.map.hex[this.y + 1][x].height
						: basicHeight;
			} else {
				heightDifL = x >= 0 ? this.map.hex[this.y][x].height
						- this.map.hex[this.y + 1][x].height : basicHeight;
				heightDifR = x + 1 < this.map.hex[this.y + 1].length ? this.map.hex[this.y][x].height
						- this.map.hex[this.y + 1][x + 1].height
						: basicHeight;
			}
		} else {
			heightDifR = basicHeight;
			heightDifL = basicHeight;
		}

		BufferedImage[] field = this.map.hex[this.y][x].graphic;
		int offsetY = hy + this.map.hexH * 3 / 4;
		int offsetX;
		int i;
		if (heightDifL > 0) {
			offsetX = ((Double) hc.x).intValue();
			g.drawImage(field[1], offsetX, offsetY,
					offsetX + field[1].getWidth() / 2,
					offsetY + field[1].getHeight(), 0, 0,
					field[1].getWidth() / 2, field[1].getHeight(),
					(ImageObserver) null);

			for (i = 1; i < heightDifL; ++i) {
				g.drawImage(field[2], offsetX,
						this.map.hexHeight * i + offsetY,
						offsetX + field[2].getWidth() / 2, this.map.hexHeight
								* i + offsetY + field[2].getHeight(), 0, 0,
						field[2].getWidth() / 2, field[2].getHeight(),
						(ImageObserver) null);
			}
		}

		if (heightDifR > 0) {
			int var10000 = ((Double) hc.x).intValue();
			this.map.getClass();
			offsetX = var10000 + 48 / 2;
			g.drawImage(field[1], offsetX, offsetY,
					offsetX + field[1].getWidth() / 2,
					offsetY + field[1].getHeight(), field[1].getWidth() / 2, 0,
					field[1].getWidth(), field[1].getHeight(),
					(ImageObserver) null);

			for (i = 1; i < heightDifR; ++i) {
				g.drawImage(field[2], offsetX,
						this.map.hexHeight * i + offsetY,
						offsetX + field[2].getWidth() / 2, this.map.hexHeight
								* i + offsetY + field[2].getHeight(),
						field[2].getWidth() / 2, 0, field[2].getWidth(),
						field[2].getHeight(), (ImageObserver) null);
			}
		}

		BufferedImage var10001 = field[0];
		int var10002 = ((Double) hc.x).intValue();
		this.map.getClass();
		g.drawImage(var10001, var10002, hy, 48 + 1, this.map.hexH + 1,
				(ImageObserver) null);
		g.setColor(Color.GRAY);
		StageMap var12 = this.map;
		var10002 = ((Double) hc.x).intValue();
		this.map.getClass();
		var12.drawHex(g, var10002, hy, 48, this.map.hexH);
	}
}
