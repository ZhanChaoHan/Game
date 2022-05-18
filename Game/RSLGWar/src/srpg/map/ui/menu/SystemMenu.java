package srpg.map.ui.menu;

import game.Sprite;
import srpg.SRPGGameObjectManager;
import srpg.map.StageMap;
import srpg.map.ui.menu.MapObjectMenu;

public class SystemMenu extends MapObjectMenu {

	public SystemMenu(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
			int y, StageMap m) {
		super(gom, sp, plnNo, x, y, m);
		this.menuStr = arr2String(Item.values());
		this.activeMenu = new boolean[] { true, true, true, true, true };
		this.width = 170;
		this.height = 150;

		this.commandHeight = 10;

		this.menuCursor = SMCursor.class;

		sp.setPos(plnNo, (gom.width - this.width) / 2,
				(gom.height - this.height) / 2);

		init();
	}

	protected class SMCursor extends MapObjectMenu.MOMenuCursor {

		public SMCursor(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
				int y, StageMap m) {
			super(gom, sp, plnNo, x, y, m);
		}

		protected void MCAction() {
			if (this.canceled) {
				this.y = SystemMenu.Item.Cancel.ordinal();
				this.selected = true;
			}

			SystemMenu.Item item = SystemMenu.Item.values()[this.y];
			if (this.selected) {
				switch (item) {
				case SwitchBGM:
					this.sp.switchBGM();
					break;
				case QuiteGame:
					this.destructor();
					this.map.destructor();
					break;
				case ChangePaintMode:
					this.sp.switchPaintMode();
					break;
				case ViewTrace:
					this.sp.switchTraceView();
					break;
				case Cancel:
					this.destructor();
				}
			}

		}

	}

	static enum Item {

		SwitchBGM, QuiteGame, ChangePaintMode, ViewTrace, Cancel;

	}
}
