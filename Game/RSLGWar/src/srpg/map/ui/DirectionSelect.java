package srpg.map.ui;

import game.Blink;
import game.Motionable;
import game.Sprite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import srpg.SRPGGameObjectManager;
import srpg.map.Direction;
import srpg.map.MapObject;
import srpg.map.StageDrawOrder;
import srpg.map.StageMap;
import srpg.map.ui.MapObjectCursor;

public class DirectionSelect extends MapObject implements Motionable {

	private StageMap m;
	DirectionSelect ds;

	public DirectionSelect(SRPGGameObjectManager gom, Sprite sp, int plnNo,
			int x, int y, StageMap m) {
		super(gom, sp, plnNo, x, y, m);
		this.m = m;
		this.ds = this;
		sp.setPos(plnNo, StageDrawOrder.MENU.z());
		this.initImg();
		gom.addMotionable(this);
		gom.createMapObject(this.map, DirectionSelect.DirCursor.class, x, y,
				this);
	}

	private void initImg() {
		Sprite var10000 = this.sp;
		int var10001 = this.plnNo;
		this.map.getClass();
		var10000.initImg(var10001, 48, this.map.hexH);
		this.map.getClass();
		this.map.getClass();
		BufferedImage buf = new BufferedImage(48, 48, 2);
		Sprite.setTransparent(buf);
		Graphics2D bufg = buf.createGraphics();
		bufg.setColor(new Color(-858993409, true));
		DirectionSelect.Tri[] var6;
		int var5 = (var6 = DirectionSelect.Tri.values()).length;

		for (int var4 = 0; var4 < var5; ++var4) {
			DirectionSelect.Tri g = var6[var4];
			g.draw(bufg);
		}

		bufg.dispose();
		Graphics2D var7 = this.sp.createGraphics(this.plnNo);
		this.map.getClass();
		var7.drawImage(buf, AffineTransform.getScaleInstance(1.0D, 0.75D),
				(ImageObserver) null);
		var7.dispose();
	}

	public void destructor() {
		this.gom.removeMotionable(this);
		super.destructor();
	}

	public void motion() {
		this.sp.setPos(this.plnNo,
				this.map.getHexCoord((double) this.x, (double) this.y));
		this.sp.setMov(this.plnNo, 0.0D, (double) (-this.map.hexH));
	}

	public int processPriority() {
		return 0;
	}

	// $FF: synthetic method
	static int access$1(DirectionSelect var0) {
		return var0.x;
	}

	// $FF: synthetic method
	static int access$2(DirectionSelect var0) {
		return var0.y;
	}

	// $FF: synthetic method
	static int access$3(DirectionSelect var0) {
		return var0.plnNo;
	}

	protected class DirCursor extends MapObjectCursor {

		DirectionSelect.Tri select = null;
		Blink bl = new Blink(256, 20);

		public DirCursor(SRPGGameObjectManager gom, Sprite sp, int plnNo,
				int x, int y, StageMap m) {
			super(gom, sp, plnNo, m.current.getDir().ordinal(), 0, m);
			sp.setPos(plnNo, this.map.getHexCoord(
					(double) DirectionSelect.access$1(DirectionSelect.this.ds),
					(double) DirectionSelect.access$2(DirectionSelect.this.ds)));
			sp.setPos(plnNo, StageDrawOrder.MENU_CURSOR.z());
			this.initImg();
			this.select();
		}

		private void initImg() {
			Sprite var10000 = this.sp;
			int var10001 = this.plnNo;
			this.map.getClass();
			var10000.initImg(var10001, 48, this.map.hexH);
		}

		private void drawImg() {
			this.map.getClass();
			this.map.getClass();
			BufferedImage buf = new BufferedImage(48, 48, 2);
			Graphics2D bufg = buf.createGraphics();
			bufg.setColor(new Color(255, 153, 153, 255 + this.bl.blinking()));
			this.select.draw(bufg);
			bufg.dispose();
			this.sp.setTransparent(this.plnNo);
			Graphics2D gr = this.sp.createGraphics(this.plnNo);
			this.map.getClass();
			gr.drawImage(buf, AffineTransform.getScaleInstance(1.0D, 0.75D),
					(ImageObserver) null);
			gr.dispose();
		}

		private void select() {
			this.move = this.moveX || this.moveY;
			this.x = (this.x + this.y + 6) % 6;
			DirectionSelect.Tri[] var4;
			int var3 = (var4 = DirectionSelect.Tri.values()).length;

			for (int var2 = 0; var2 < var3; ++var2) {
				DirectionSelect.Tri t = var4[var2];
				if (this.x == t.ordinal()) {
					this.select = t;
					this.map.current.setDir(this.select.dir);
				}
			}

			this.y = 0;
		}

		protected void mainAction() {
			this.sp.setPos(this.plnNo, this.sp.getPos(DirectionSelect
					.access$3(DirectionSelect.this.ds)));
			this.select();
			this.drawImg();
			if (this.selected) {
				this.destructor();
				DirectionSelect.this.ds.destructor();
				super.select = true;
			} else if (this.canceled) {
				this.map.current.returnPrevious();
				this.destructor();
				DirectionSelect.this.ds.destructor();
			}

		}
	}

	static enum Tri {

		UPRIGHT("UPRIGHT", 0, Direction.UPRIGHT, (DirectionSelect.Tri) null) {
			protected void mainDraw(Graphics2D g) {

				double var10001 = (double) (48 * 3 / 4);

				this.at = AffineTransform.getTranslateInstance(var10001,
						(double) (48 / 8));
				this.at.rotate(0.5235987755982988D);
			}
		},
		RIGHT("RIGHT", 1, Direction.RIGHT, (DirectionSelect.Tri) null) {
			protected void mainDraw(Graphics2D g) {

				this.at = AffineTransform.getTranslateInstance(48.0D,
						(double) (48 / 2));
				this.at.rotate(1.5707963267948966D);
			}
		},
		DOWNRIGHT("DOWNRIGHT", 2, Direction.DOWNRIGHT,
				(DirectionSelect.Tri) null) {
			protected void mainDraw(Graphics2D g) {

				double var10001 = (double) (48 * 3 / 4);

				this.at = AffineTransform.getTranslateInstance(var10001,
						(double) (48 * 7 / 8));
				this.at.rotate(2.6179938779914944D);
			}
		},
		DOWNLEFT("DOWNLEFT", 3, Direction.DOWNLEFT, (DirectionSelect.Tri) null) {
			protected void mainDraw(Graphics2D g) {

				double var10001 = (double) (48 / 4);

				this.at = AffineTransform.getTranslateInstance(var10001,
						(double) (48 * 7 / 8));
				this.at.rotate(-2.6179938779914944D);
			}
		},
		LEFT("LEFT", 4, Direction.LEFT, (DirectionSelect.Tri) null) {
			protected void mainDraw(Graphics2D g) {

				this.at = AffineTransform.getTranslateInstance(0.0D,
						(double) (48 / 2));
				this.at.rotate(-1.5707963267948966D);
			}
		},
		UPLEFT("UPLEFT", 5, Direction.UPLEFT, (DirectionSelect.Tri) null) {
			protected void mainDraw(Graphics2D g) {

				double var10001 = (double) (48 / 4);

				this.at = AffineTransform.getTranslateInstance(var10001,
						(double) (48 / 8));
				this.at.rotate(-0.5235987755982988D);
			}
		};
		static final int width = 16;
		static final int height = 8;
		static final Polygon triangle = new Polygon(new int[] { 0, -8, 8 },
				new int[] { 0, 8, 8 }, 3);
		AffineTransform at;
		AffineTransform def;
		final Direction dir;

		private Tri(String var1, int var2, Direction dir) {
			this.dir = dir;
		}

		protected abstract void mainDraw(Graphics2D var1);

		void draw(Graphics2D g) {
			this.def = g.getTransform();
			this.mainDraw(g);
			g.transform(this.at);
			g.fillPolygon(triangle);
			g.setTransform(this.def);
		}

		Tri(String var1, int var2, Direction var3, DirectionSelect.Tri var4) {
			this(var1, var2, var3);
		}
	}
}
