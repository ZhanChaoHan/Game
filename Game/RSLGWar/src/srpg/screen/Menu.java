// Decompiled by:       Fernflower v0.6
// Date:                24.10.2010 08:56:31
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package srpg.screen;

import game.Draw;
import game.DrawOrder;
import game.GameObject;
import game.GameObjectManager;
import game.Plane;
import game.Sprite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import myutil.Coord;
import srpg.screen.Cursor;

public abstract class Menu extends GameObject {

	protected final Menu menu = this;
	protected Coord offset = new Coord(Integer.valueOf(0), Integer.valueOf(0));
	protected int width;
	protected int height;
	protected String[] menuStr;
	protected boolean[] activeMenu;
	protected int commandHeight;
	protected Class menuCursor;
	protected boolean repaint = false;
	protected int span;

	public Menu(GameObjectManager gom, Sprite sp, int plnNo, int x, int y) {
		super(gom, sp, plnNo, x, y);
	}

	private void initImg() {
		this.sp.initImg(this.plnNo, this.width + 1, this.height + 1);
		this.repaint = true;
		this.drawImg();
	}

	private void drawImg() {
		if (this.repaint) {
			this.repaint = false;
			this.sp.setTransparent(this.plnNo);
			Graphics2D gbg = this.sp.createGraphics(this.plnNo);
			this.x = 0;
			this.y = 0;
			this.drawBGRect(this.x, this.y, this.width, this.height, gbg);

			for (int i = 0; i < this.activeMenu.length; ++i) {
				if (this.activeMenu[i]) {
					gbg.setColor(Color.BLACK);
				} else {
					gbg.setColor(Color.GRAY);
				}

				Sprite.drawShadedString(this.menuStr[i], this.x
						+ ((Integer) this.offset.x).intValue() + 30, this.y
						+ ((Integer) this.offset.y).intValue()
						+ (this.span + this.commandHeight) * (i + 1), gbg);
			}

			gbg.dispose();
		}
	}

	protected void init() {
		this.span = (this.height - this.menuStr.length * this.commandHeight)
				/ (this.menuStr.length + 1);
		this.createCursor(this.sp.getPos(this.plnNo));
		this.initImg();
	}

	protected abstract void createCursor(Coord var1);

	protected abstract void drawBGRect(int var1, int var2, int var3, int var4,
			Graphics var5);

	protected String[] arr2String(Object[] o) {
		String[] s = new String[o.length];

		for (int i = 0; i < o.length; ++i) {
			s[i] = o[i].toString();
		}

		return s;
	}

	static int access$1(Menu var0) {
		return var0.plnNo;
	}

	protected abstract class MenuCursor extends Cursor {

		protected int size;
		protected Coord current;

		public MenuCursor(GameObjectManager gom, Sprite sp, int plnNo, int x,
				int y) {
			super(gom, sp, plnNo, x, y);
			this.size = Menu.this.commandHeight + 4;
			this.current = new Coord(Integer.valueOf(x), Integer.valueOf(y));
			this.x = this.y = 0;
			sp.setPos(plnNo, DrawOrder.CURSOR.z());
			sp.setDraw(plnNo, new Draw() {

				int count = 0;
				int x;
				int y;
				Polygon po;

				public void drawing(Graphics g, Plane pln) {
					g.setColor(new Color(170, 255, 170, 255 - this.count * 16));
					this.x = ((Double) pln.coord.x).intValue();
					this.y = ((Double) pln.coord.y).intValue();
					this.po = new Polygon(new int[] { this.x,
							this.x + MenuCursor.this.size, this.x }, new int[] {
							this.y, this.y + MenuCursor.this.size / 2,
							this.y + MenuCursor.this.size }, 3);
					g.fillPolygon(this.po);
					g.setColor(new Color(68, 136, 68, 255 - this.count * 16));
					g.drawPolygon(this.po);
					++this.count;
					this.count %= 12;
				}
			});
		}

		public void destructor() {
			super.destructor();
			Menu.this.menu.destructor();
		}

		protected void mainAction() {
			Menu.this.menu.drawImg();
			this.move = this.moveY;
			this.y = (this.y + Menu.this.menuStr.length * 10)
					% Menu.this.menuStr.length;
			if (this.selected && Menu.this.activeMenu[this.y]) {
				this.select = true;
			}

			this.sp.setPos(this.plnNo,
					(double) ((Integer) this.current.x).intValue(),
					(double) (((Integer) this.current.y).intValue()
							+ Menu.this.span * this.y + Menu.this.commandHeight
							* this.y));
			this.MCAction();
		}

		protected void setView(boolean view) {
			this.sp.setView(this.plnNo, view);
			this.sp.setView(Menu.access$1(Menu.this.menu), view);
		}

		protected abstract void MCAction();
	}
}
