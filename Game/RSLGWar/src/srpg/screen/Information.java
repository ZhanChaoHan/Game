package srpg.screen;

import game.ChangeTracker;
import game.GameObject;
import game.GameObjectManager;
import game.Motionable;
import game.Sprite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import myutil.Coord;

public abstract class Information extends GameObject implements Motionable {

	protected int width;
	protected int height;
	protected String title;
	protected Information.StrData[] sds;
	protected Coord current = new Coord(Integer.valueOf(0), Integer.valueOf(0));
	protected ChangeTracker ct = new ChangeTracker();
	BufferedImage bgRect;

	public Information(GameObjectManager gom, Sprite sp, int plnNo, int x, int y) {
		super(gom, sp, plnNo, x, y);
		gom.addMotionable(this);
	}

	public void destructor() {
		this.gom.removeMotionable(this);
		super.destructor();
	}

	protected void init(int width, int height) {
		this.width = width;
		this.height = height;
		this.initImg();
	}

	private void initImg() {
		this.sp.initImg(this.plnNo, this.width + 1, this.height + 3 + 1);
		this.bgRect = new BufferedImage(this.width + 1, this.height + 1, 2);
		Sprite.setTransparent(this.bgRect);
		Graphics2D g = this.bgRect.createGraphics();
		this.drawBGRect(0, 0, this.width, this.height, g);
		g.dispose();
	}

	private void drawImg() {
		if (this.sds != null) {
			this.sp.setTransparent(this.plnNo);
			byte x = 0;
			byte y = 4;
			Graphics2D g = this.sp.createGraphics(this.plnNo);
			g.drawImage(this.bgRect, 0, 3, (ImageObserver) null);
			g.setColor(Color.BLACK);
			Sprite.drawShadedString(this.title, x + 20, y + 5, g);
			Information.StrData[] var7 = this.sds;
			int var6 = this.sds.length;

			for (int var5 = 0; var5 < var6; ++var5) {
				Information.StrData sd = var7[var5];
				g.setColor(sd.c);
				Sprite.drawShadedString(sd.str, x + sd.x, y + sd.y, g);
			}

			g.dispose();
		}
	}

	protected abstract void drawBGRect(int var1, int var2, int var3, int var4,
			Graphics var5);

	public void motion() {
		this.ct.isChanged(this.current.clone());
		this.update();
		this.mainMotion();
		if (this.ct.isChanged(this.current.clone())) {
			this.drawImg();
		}

	}

	protected abstract void update();

	public abstract void mainMotion();

	public int processPriority() {
		return -1;
	}

	protected class StrData {

		public String str;
		int x;
		int y;
		public Color c;

		public StrData(String str, int x, int y) {
			this.c = Color.BLACK;
			this.str = str;
			this.x = x;
			this.y = y;
		}

		public String toString() {
			return this.str;
		}
	}
}
