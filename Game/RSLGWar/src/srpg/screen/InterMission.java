package srpg.screen;

import game.GameObject;
import game.GameObjectManager;
import game.Sprite;
import java.util.ArrayList;
import srpg.Player;
import srpg.UnitStatus;
import srpg.screen.ScreenMenu;
import srpg.screen.StageSelect;
import srpg.screen.Status;
import srpg.screen.talk.Talk;

public class InterMission extends ScreenMenu {

	private Player player;

	public InterMission(GameObjectManager gomm, Sprite sp, int plnNo, int x,
			int y) {
		super(gomm, sp, plnNo, x, y);

		this.menuStr = arr2String(Item.values());
		this.activeMenu = new boolean[] { true, false, false, true };

		this.menuCursor = IMCursor.class;

		init();

		this.player = new Player(new ArrayList(), 0);

		UnitStatus.initParty(this.player.party);
	}

	protected class IMCursor extends ScreenMenu.SMCursor {

		public IMCursor(GameObjectManager gom, Sprite sp, int plnNo, int x,
				int y) {
			super(gom, sp, plnNo, x, y);
		}

		protected void MCAction() {
			this.setView(true);
			this.sp.playBGM(Sprite.BGM.TITLE);
			InterMission.Item item = InterMission.Item.values()[this.y];
			if (this.select) {
				switch (item) {
				case Stage_Select:
					this.setView(false);
					((StageSelect) this.gom.addGO(StageSelect.class, 0, 0,
							new GameObject[0]))
							.setParty(InterMission.this.player);
					break;
				case Status:
					this.setView(false);
					((Status) this.gom.addGO(Status.class, 0, 0,
							new GameObject[0]))
							.initParty(InterMission.this.player.party);
				case Equip:
				default:
					break;
				case About:
					this.gom.addGO(Talk.class, 0, 0, new GameObject[0]);
				}
			}

		}

	}

	static enum Item {
		Stage_Select, Status, Equip, About;

	}
}
