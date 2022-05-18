package srpg.screen;

import game.GameObjectManager;
import game.Key;
import game.Sprite;
import game.UserIF;

public abstract class Cursor extends UserIF {

	protected boolean select;
	protected boolean cancel;
	protected boolean failure;
	protected boolean moveX;
	protected boolean moveY;
	protected boolean move;
	protected boolean selected;
	protected boolean canceled;

	public Cursor(GameObjectManager gom, Sprite sp, int plnNo, int x, int y) {
		super(gom, sp, plnNo, x, y);
	}

	public void action() {
		this.selected = this.canceled = this.select = this.cancel = this.moveX = this.moveY = this.move = false;

		Key key;

		while ((key = (Key) keyQ.dequeue()) != null) {
			switch (key) {
			case UP:
				--this.y;
				this.moveY = true;
				break;
			case DOWN:
				++this.y;
				this.moveY = true;
				break;
			case LEFT:
				--this.x;
				this.moveX = true;
				break;
			case RIGHT:
				++this.x;
				this.moveX = true;
				break;
			case SELECT:
				this.selected = true;
				break;
			case CANCEL:
				this.canceled = true;
				break;
			}
		}

		this.failure = this.selected;
		this.cancel = this.canceled;
		this.mainAction();
		if (this.cancel) {
			this.sp.playSE(Sprite.Preset.CANCEL);
		} else if (this.select) {
			this.sp.playSE(Sprite.Preset.SELECT);
		} else if (this.failure) {
			this.sp.playSE(Sprite.Preset.FAILURE);
		} else if (this.move) {
			this.sp.playSE(Sprite.Preset.MOVE);
		}

	}

	protected abstract void mainAction();

}
