package srpg.screen;

import game.DrawOrder;
import game.GameObjectManager;
import game.Key;
import game.Sprite;
import game.UserIF;
import java.awt.Color;
import java.awt.Graphics;

public abstract class Screen extends UserIF {

	protected int y = 0;
	protected boolean selected;

	public Screen(GameObjectManager gom, Sprite sp, int plnNo, int x, int y) {
		super(gom, sp, plnNo, x, y);
		sp.setPos(plnNo, DrawOrder.SCREEN.z());
	}

	void drawCenterString(String str, int x, int y, Graphics g) {
		g.drawString(str, x - str.length() * g.getFont().getSize() / 4, y);
	}

	public void action() {
		this.selected = false;
		Key key;
		while ((key = (Key) keyQ.dequeue()) != null) {
			switch (key) {
			case SELECT:
				this.selected = true;
				break;
			case CANCEL:
				this.y += 1;
				break;
			case DOWN:
				this.y -= 1;
			case LEFT:
			case RIGHT:
			}
		}
		mainAction();
	}

	protected void clear(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.gom.width, this.gom.height);
	}

	protected abstract void mainAction();

}
