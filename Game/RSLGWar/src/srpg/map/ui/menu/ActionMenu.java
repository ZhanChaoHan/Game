package srpg.map.ui.menu;

import game.Sprite;
import srpg.Action;
import srpg.SRPGGameObjectManager;
import srpg.map.StageMap;
import srpg.map.ui.area.AttackableArea;
import srpg.map.ui.menu.MapObjectMenu;

public class ActionMenu extends MapObjectMenu {

	protected Action[] action;

	public ActionMenu(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
			int y, StageMap m) {
		super(gom, sp, plnNo, x, y, m);
	}

	protected void init(Action[] a, Class<?> cursor) {
		this.action = a;
		this.menuStr = arr2String(this.action);
		this.activeMenu = new boolean[this.menuStr.length];
		for (int i = 0; i < this.activeMenu.length; i++)
			this.activeMenu[i] = this.action[i].usable(this.map.current
					.getStatus());
		this.width = 200;
		this.height = 150;
		this.commandHeight = 10;

		this.menuCursor = cursor;

		this.sp.setPos(this.plnNo, (this.gom.width - this.width) / 2,
				(this.gom.height - this.height) / 2);

		init();
	}

	protected class AMCursor extends MapObjectMenu.MOMenuCursor {

		public AMCursor(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
				int y, StageMap m) {
			super(gom, sp, plnNo, x, y, m);
		}

		protected void MCAction() {
			this.setView(true);
			if (!this.map.current.isAttackable()) {
				this.canceled = true;
			}

			if (this.canceled) {
				this.map.current.setCurrentAction((Action) null);
				this.destructor();
			} else {
				this.map.current
						.setCurrentAction(ActionMenu.this.action[this.y]);
				if (this.selected && ActionMenu.this.activeMenu[this.y]) {
					this.setView(false);
					this.map.createMapObject(AttackableArea.class,
							((Integer) this.map.current.getPos().x).intValue(),
							((Integer) this.map.current.getPos().y).intValue());
				}

			}
		}
	}
}
