package srpg.map.ui.menu;

import game.Sprite;
import srpg.SRPGGameObjectManager;
import srpg.map.StageMap;
import srpg.map.ui.menu.ActionMenu;

public class ItemMenu extends ActionMenu {

	public ItemMenu(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
			int y, StageMap m) {
		super(gom, sp, plnNo, x, y, m);
		this.init(this.map.current.getStatus().capa.item,
				ItemMenu.IMCursor.class);
	}

	protected class IMCursor extends ActionMenu.AMCursor {

		public IMCursor(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
				int y, StageMap m) {
			super(gom, sp, plnNo, x, y, m);
		}
	}
}
