package srpg.screen.talk;

import game.GameObjectManager;
import game.Key;
import game.Sprite;
import java.awt.Graphics;
import srpg.screen.ScreenMenu;
import srpg.screen.talk.TalkDrawOrder;

public class Question extends ScreenMenu {

	private int result;

	public Question(GameObjectManager gom, Sprite sp, int plnNo, int x, int y) {
		super(gom, sp, plnNo, x, y);
		sp.setPos(plnNo, TalkDrawOrder.QUESTION.z());
		this.menuCursor = Question.QCursor.class;
		this.width = 170;
		this.height = 150;
		sp.setPos(plnNo, (double) ((gom.width - this.width) / 2),
				(double) ((gom.height - this.height) / 2));
		this.offset.x = Integer
				.valueOf(((Integer) this.offset.x).intValue() - 40);
	}

	void init(String[] strs, boolean[] bs) {
		this.menuStr = strs;
		this.activeMenu = bs;
		super.init();
	}

	int getResult() {
		return this.result;
	}

	protected void drawBGRect(int x, int y, int width, int height, Graphics g) {
		Sprite.drawDesignedRect(x, y, width, height, g);
	}

	protected class QCursor extends ScreenMenu.SMCursor {

		public QCursor(GameObjectManager gom, Sprite sp, int plnNo, int x, int y) {
			super(gom, sp, plnNo, x, y);
			sp.setPos(plnNo, TalkDrawOrder.Q_CURSOR.z());
		}

		public void destructor() {
			keyQ.enqueue(Key.SELECT);
			super.destructor();
		}

		protected void MCAction() {
			if (this.select) {
				this.destructor();
				Question.this.result = this.y;
			}

		}
	}
}
