package srpg.screen;

import game.DrawOrder;
import game.GameObject;
import game.GameObjectManager;
import game.Sprite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import myutil.Coord;
import srpg.screen.Menu;

public abstract class ScreenMenu extends Menu {

	private BufferedImage img;

	public ScreenMenu(GameObjectManager gom, Sprite sp, int plnNo, int x, int y) {
		super(gom, sp, plnNo, x, y);

		this.width = gom.width;
		this.height = gom.height;
		this.commandHeight = 10;

		sp.setPos(plnNo, DrawOrder.SCREEN_MENU.z());
		sp.setPos(plnNo, 0.0D, 0.0D);

		this.offset = new Coord(Integer.valueOf(70), Integer.valueOf(0));

		String file = "shade_marisa";
		sp.addGrp(file);
		this.img = sp.getGrp(file);
	}

	protected void createCursor(Coord current) {
		this.gom.addGO(
				this.menuCursor,
				((Double) current.x).intValue()
						+ ((Integer) this.offset.x).intValue()
						+ this.commandHeight,
				((Double) current.y).intValue()
						+ ((Integer) this.offset.y).intValue() + this.span,
				new GameObject[] { this });
	}

	protected void drawBGRect(int x, int y, int width, int height, Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
		g.drawImage(this.img, x + width - this.img.getWidth(), y - 50,
				(ImageObserver) null);
	}

	protected abstract class SMCursor extends Menu.MenuCursor {

		public SMCursor(GameObjectManager gom, Sprite sp, int plnNo, int x,
				int y) {
			super(gom, sp, plnNo, x, y);
			sp.setPos(plnNo, DrawOrder.CURSOR.z());
		}
	}
}
