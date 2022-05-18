package srpg.map.ui.menu;

import game.Sprite;
import srpg.SRPGGameObjectManager;
import srpg.map.StageMap;
import srpg.map.ui.menu.ActionMenu;

public class SkillMenu extends ActionMenu {

	public SkillMenu(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
			int y, StageMap m) {
		super(gom, sp, plnNo, x, y, m);
		this.init(this.map.current.getStatus().capa.skill,
				SkillMenu.SMCursor.class);
	}

	protected class SMCursor extends ActionMenu.AMCursor {

		public SMCursor(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
				int y, StageMap m) {
			super(gom, sp, plnNo, x, y, m);
		}
	}
}
