package srpg.screen;

import game.GameObject;
import game.GameObjectManager;
import game.Sprite;
import srpg.Player;
import srpg.map.StageMap;
import srpg.screen.ScreenMenu;

public class StageSelect extends ScreenMenu {

	private Player player;

	public StageSelect(GameObjectManager gom, Sprite sp, int plnNo, int x, int y) {
		super(gom, sp, plnNo, x, y);
		this.menuStr = arr2String(Item.values());
		this.activeMenu = new boolean[] { true, true, true };

		this.menuCursor = SSCursor.class;

		init();
	}

	void setParty(Player player) {
		this.player = player;
	}

	protected class SSCursor extends ScreenMenu.SMCursor {

		public SSCursor(GameObjectManager gom, Sprite sp, int plnNo, int x,
				int y) {
			super(gom, sp, plnNo, x, y);
		}

		protected void MCAction() {
			if (this.canceled) {
				this.destructor();
			} else {
				StageSelect.Item item = StageSelect.Item.values()[this.y];
				if (this.select) {
					this.destructor();
					((StageMap) this.gom.addGO(StageMap.class, 20, 20,
							new GameObject[0])).init(item.file,
							StageSelect.this.player);
				}

			}
		}
	}

	static enum Item {

		Stage1("Stage1", 0, "stage1.txt"), Stage2("Stage2", 1, "stage2.txt"), Stage3(
				"Stage3", 2, "stage3.txt");
		String file;

		private Item(String var1, int var2, String file) {
			this.file = file;
		}
	}
}
