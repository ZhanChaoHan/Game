package game;

import game.GameObject;
import game.GameObjectManager;
import game.Sprite;
import myutil.Queue;

public abstract class UserIF extends GameObject {

	protected static Queue keyQ;

	public UserIF(GameObjectManager gom, Sprite sp, int plnNo, int x, int y) {
		super(gom, sp, plnNo, x, y);
		gom.setUIF(this);
	}

	public abstract void action();

	public void destructor() {
		this.gom.removeUIF(this);
		super.destructor();
	}

	static void setKeyQ(Queue e) {
		keyQ = e;
	}
}
