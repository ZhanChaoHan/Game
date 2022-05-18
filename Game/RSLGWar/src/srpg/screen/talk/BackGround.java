package srpg.screen.talk;

import game.GameObject;
import game.GameObjectManager;
import game.Sprite;
import srpg.screen.talk.TalkDrawOrder;

public class BackGround extends GameObject {

	public BackGround(GameObjectManager gomm, Sprite sp, int plnNo, int x, int y) {
		super(gomm, sp, plnNo, x, y);
		sp.setPos(plnNo, TalkDrawOrder.BG.z());
	}

	void setImg(String file) {
		sp.addGrp(file);
		sp.setImg(plnNo, file);
	}
}