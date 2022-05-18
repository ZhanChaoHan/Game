// Decompiled by:       Fernflower v0.6
// Date:                24.10.2010 08:56:26
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package srpg.map.ui;

import game.Draw;
import game.Motionable;
import game.Plane;
import game.Sprite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import myutil.Coord;
import srpg.SRPGGameObjectManager;
import srpg.map.MapDrawOrder;
import srpg.map.StageMap;
import srpg.map.ui.MapObjectCursor;

public abstract class MapCursor extends MapObjectCursor implements Motionable {

	protected Color color = new Color('\uff00');
	protected boolean actionFlag = false;
	private final int leftOffset = 200;
	private final int rightOffset = 220;
	private final int topOffset = 160;
	private final int bottomOffset = 190;

	public MapCursor(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
			int y, StageMap m) {
		super(gom, sp, plnNo, x, y, m);
		sp.setPos(plnNo, MapDrawOrder.MAP_CURSOR.z() + (double) y
				* 0.00392156862745098D);
		sp.setDraw(plnNo, new Draw() {

			int count = 0;
			int c = 1;

			public void drawing(Graphics g, Plane pln) {
				if (MapCursor.this.actionFlag) {
					MapCursor.this.actionFlag = false;
					g.setColor(Color.WHITE);
					int over = -MapCursor.this.map.hexH * 3 / 2;
					int w = 18 * this.count / 10;
					byte h = 12;
					int[] var10002 = new int[3];
					MapCursor.this.map.getClass();
					var10002[0] = (48 - w) / 2;
					MapCursor.this.map.getClass();
					var10002[1] = (48 + w) / 2;
					MapCursor.this.map.getClass();
					var10002[2] = 48 / 2;
					Polygon po = new Polygon(var10002, new int[] { 0, 0, h }, 3);
					po.translate(((Double) pln.coord.x).intValue(),
							((Double) pln.coord.y).intValue() + over);
					g.fillPolygon(po);
					g.setColor(Color.GRAY);
					g.drawPolygon(po);
				}

				g.setColor(new Color(MapCursor.this.color.getRed(),
						MapCursor.this.color.getGreen(), MapCursor.this.color
								.getBlue(), 84 - 5 * this.count));
				this.count += this.c;
				if (this.count <= 0 || this.count >= 10) {
					this.c *= -1;
				}

				byte over1 = 2;
				StageMap var10000 = MapCursor.this.map;
				int var7 = (int) Math.round(((Double) pln.coord.x)
						.doubleValue()) - over1;
				int var10003 = (int) Math.round(((Double) pln.coord.y)
						.doubleValue()) - over1;
				MapCursor.this.map.getClass();
				var10000.fillHex(g, var7, var10003, 48 + over1 * 2,
						MapCursor.this.map.hexH + over1 * 2);
				g.setColor(Color.BLACK);
			}
		});
		sp.setPos(plnNo, this.map.getHexCoord((double) x, (double) y));
		this.map.setCursorPos(x, y);
		gom.addMotionable(this);
	}

	protected void mainAction() {
		this.move = this.moveX || this.moveY;
		this.actionFlag = true;
		this.x = (this.x + this.map.hex[0].length) % this.map.hex[0].length;
		this.y = (this.y + this.map.hex.length) % this.map.hex.length;
		this.map.setCursorPos(this.x, this.y);
		Coord currentC = this.map.getHexCoord((double) this.x, (double) this.y);
		double var10001 = ((Double) currentC.x).doubleValue();
		this.map.getClass();
		currentC.x = Double.valueOf(var10001 + (double) (48.0F / 2.0F));
		currentC.y = Double.valueOf(((Double) currentC.y).doubleValue()
				+ (double) ((float) this.map.hexH / 4.0F));
		Coord move = new Coord(Double.valueOf(0.0D), Double.valueOf(0.0D));
		if (((Double) currentC.x).doubleValue() < 200.0D) {
			move.x = Double.valueOf(((Double) move.x).doubleValue() + 200.0D
					- ((Double) currentC.x).doubleValue() + 1.0D);
		} else if ((double) (this.gom.width - 220) < ((Double) currentC.x)
				.doubleValue()) {
			move.x = Double.valueOf(((Double) move.x).doubleValue()
					+ ((double) (this.gom.width - 220)
							- ((Double) currentC.x).doubleValue() - 1.0D));
		}

		if (((Double) currentC.y).doubleValue() < 160.0D) {
			move.y = Double.valueOf(((Double) move.y).doubleValue() + 160.0D
					- ((Double) currentC.y).doubleValue() + 1.0D);
		} else if ((double) (this.gom.height - 190) < ((Double) currentC.y)
				.doubleValue()) {
			move.y = Double.valueOf(((Double) move.y).doubleValue()
					+ ((double) (this.gom.height - 190)
							- ((Double) currentC.y).doubleValue() - 1.0D));
		}

		this.map.moveMap(((Double) move.x).doubleValue(),
				((Double) move.y).doubleValue());
		this.MCAction();
	}

	public void motion() {
		this.sp.setPos(this.plnNo,
				this.map.getHexCoord((double) this.x, (double) this.y));
		this.sp.setPos(this.plnNo, MapDrawOrder.MAP_CURSOR.z()
				+ (double) this.y * 0.00392156862745098D);
	}

	public int processPriority() {
		return 0;
	}

	protected abstract void MCAction();

	public void destructor() {
		super.destructor();
		this.gom.removeMotionable(this);
	}
}
