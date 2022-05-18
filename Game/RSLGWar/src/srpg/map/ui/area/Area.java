package srpg.map.ui.area;

import game.Draw;
import game.Plane;
import game.Sprite;
import java.awt.Color;
import java.awt.Graphics;
import myutil.Coord;
import srpg.SRPGGameObjectManager;
import srpg.map.HorizonHex;
import srpg.map.MapDrawOrder;
import srpg.map.MapObject;
import srpg.map.StageMap;
import srpg.map.ui.MapCursor;

public abstract class Area extends MapObject {

	protected Area area = this;
	protected int range;
	protected boolean[][] availables;
	protected Color color;
	Area.HorizonArea[] ha;

	public Area(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x, int y,
			StageMap m) {
		super(gom, sp, plnNo, x, y, m);
		this.ha = new Area.HorizonArea[this.map.hex.length];
		sp.setDraw(plnNo, new Draw() {
			public void drawing(Graphics g, Plane pln) {
			}
		});
	}

	public void destructor() {
		Area.HorizonArea[] var4 = this.ha;
		int var3 = this.ha.length;

		for (int var2 = 0; var2 < var3; ++var2) {
			Area.HorizonArea h = var4[var2];
			h.destructor();
		}

		super.destructor();
	}

	protected boolean isAvailable(int x, int y) {
		return x >= 0 && this.availables.length > x && y >= 0
				&& this.availables[x].length > y ? this.availables[x][y]
				: false;
	}

	protected void createHorizon(Class h) {
		for (int i = 0; i < this.map.hex.length; ++i) {
			this.ha[i] = (Area.HorizonArea) this.gom.createMapObject(this.map,
					h, 0, i, this.area);
		}

	}

	protected class HorizonArea extends HorizonHex {

		public HorizonArea(SRPGGameObjectManager gom, Sprite sp, int plnNo,
				int x, int hy, StageMap m) {
			super(gom, sp, plnNo, x, hy, m);
			sp.setPos(plnNo, MapDrawOrder.AREA.z() + (double) this.y
					* 0.00392156862745098D);
		}

		protected void drawEachHex(Graphics g, int x) {
			g.setColor(Area.this.color);
			if (Area.this.availables != null && Area.this.availables[x][this.y]) {
				Coord hc = this.map.getHexCoord((double) x, (double) this.y,
						false);
				StageMap var10000 = this.map;
				int var10002 = ((Double) hc.x).intValue();
				int var10003 = (int) (((Double) hc.y).doubleValue() - ((Double) this.offset.y)
						.doubleValue()) - 1;
				this.map.getClass();
				var10000.fillHex(g, var10002, var10003, 48, this.map.hexH,
						0.25F);
			}

		}
	}

	protected abstract class AreaCursor extends MapCursor {

		public AreaCursor(SRPGGameObjectManager gom, Sprite sp, int plnNo,
				int x, int y, StageMap m) {
			super(gom, sp, plnNo, x, y, m);
		}

		public void destructor() {
			super.destructor();
			Area.this.area.destructor();
		}
	}
}
