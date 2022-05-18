package srpg.map.ui.menu;

import game.Sprite;
import srpg.Action;
import srpg.SRPGGameObjectManager;
import srpg.map.StageMap;
import srpg.map.ui.DirectionSelect;
import srpg.map.ui.area.AttackableArea;
import srpg.map.ui.area.MovableArea;
import srpg.map.ui.menu.ItemMenu;
import srpg.map.ui.menu.MapObjectMenu;
import srpg.map.ui.menu.SkillMenu;
import srpg.map.ui.menu.SystemMenu;

public class CommandMenu extends MapObjectMenu {

	public CommandMenu(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
			int y, StageMap m) {
		super(gom, sp, plnNo, x, y, m);
		this.menuStr = arr2String(Item.values());

		boolean skill = false;
		boolean item = false;

		for (Action act : this.map.current.getStatus().capa.skill) {
			skill = (skill) || (act.usable(this.map.current.getStatus()));
		}
		for (Action act : this.map.current.getStatus().capa.item) {
			item = (item) || (act.usable(this.map.current.getStatus()));
		}
		this.activeMenu = new boolean[] { this.map.current.isMovable(),
				this.map.current.isAttackable(), skill, item, true, true };
		this.width = 110;
		this.height = 170;
		this.commandHeight = 10;

		this.menuCursor = CMCursor.class;
		sp.setPos(plnNo, 30.0D, 20.0D);

		init();
	}

	static void access$0(CommandMenu var0, boolean var1) {
		var0.repaint = var1;
	}

	static boolean[] access$1(CommandMenu var0) {
		return var0.activeMenu;
	}

	protected class CMCursor extends MapObjectMenu.MOMenuCursor {

		boolean afterMove = false;

		public CMCursor(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
				int y, StageMap m) {
			super(gom, sp, plnNo, x, y, m);
		}

		protected void MCAction() {
			if (this.map.isDestracted()) {
				this.destructor();
			} else if (this.afterMove && !this.map.current.isMovable()) {
				this.gom.createMapObject(this.map, DirectionSelect.class,
						((Integer) this.map.current.getPos().x).intValue(),
						((Integer) this.map.current.getPos().y).intValue());
				this.afterMove = false;
			} else {
				this.afterMove = false;
				this.map.setCursorPos(this.map.current.getPos());
				if (!this.sp.getView(this.plnNo)) {
					CommandMenu.access$0(CommandMenu.this, true);
					this.setView(true);
				}

				CommandMenu.access$1(CommandMenu.this)[CommandMenu.Item.Move
						.ordinal()] = this.map.current.isMovable();
				CommandMenu.access$1(CommandMenu.this)[CommandMenu.Item.Attack
						.ordinal()] = this.map.current.isAttackable();
				if (!this.map.current.isAttackable()) {
					this.selected = true;
					this.y = CommandMenu.Item.Standby.ordinal();
				}

				if (this.canceled) {
					if (this.map.current.isMovable()
							|| !this.map.current.isAttackable()) {
						this.destructor();
						return;
					}

					this.map.current.returnPrevious();
					CommandMenu.access$1(CommandMenu.this)[CommandMenu.Item.Move
							.ordinal()] = this.map.current.isMovable();
					CommandMenu.access$0(CommandMenu.this, true);
					this.y = CommandMenu.Item.Move.ordinal();
				}

				CommandMenu.Item item = CommandMenu.Item.values()[this.y];
				if (this.selected
						&& CommandMenu.access$1(CommandMenu.this)[item
								.ordinal()]) {
					switch (item) {
					case Move:
						this.setView(false);
						this.map.createMapObject(MovableArea.class,
								((Integer) this.map.current.getPos().x)
										.intValue(),
								((Integer) this.map.current.getPos().y)
										.intValue());
						this.afterMove = true;
						break;
					case Attack:
						this.setView(false);
						this.map.current.setCurrentAction(this.map.current
								.getStatus().capa.attack);
						this.map.createMapObject(AttackableArea.class,
								((Integer) this.map.current.getPos().x)
										.intValue(),
								((Integer) this.map.current.getPos().y)
										.intValue());
						break;
					case Skill:
						this.setView(false);
						this.gom.createMapObject(this.map, SkillMenu.class, 0,
								0);
						break;
					case Item:
						this.setView(false);
						this.gom.createMapObject(this.map, ItemMenu.class, 0, 0);
						break;
					case System:
						this.gom.createMapObject(this.map, SystemMenu.class, 0,
								0);
						break;
					case Standby:
						this.destructor();
						this.map.current.setCurrentAction((Action) null);
						this.map.current.setActionable(false);
					}
				}

			}
		}

	}

	static enum Item {

		Move, Attack, Skill, Item, System, Standby;

	}
}
