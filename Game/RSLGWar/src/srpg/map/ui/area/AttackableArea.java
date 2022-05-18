package srpg.map.ui.area;

import game.ChangeTracker;
import game.Sprite;
import java.awt.Color;
import srpg.Action;
import srpg.ActionType;
import srpg.SRPGGameObjectManager;
import srpg.map.StageMap;
import srpg.map.UnitType;
import srpg.map.ui.area.Area;

public class AttackableArea extends Area {

	Area aaarea;
	ActionType act;

	public AttackableArea(SRPGGameObjectManager gom, Sprite sp, int plnNo,
			int x, int y, StageMap m) {
		super(gom, sp, plnNo, x, y, m);
		this.act = this.map.current.getCurrentAction().actType;
		switch (this.act) {
		case ATTACK:
		case ALL:
			this.color = new Color(16711680);
			break;
		case SUPPORT:
			this.color = new Color(0xffff);
		}

		this.availables = this.map.current.getAttackableArea();
		gom.createMapObject(this.map, AttackableArea.AttackCursor.class, x, y,
				this);
		this.createHorizon(AttackableArea.AttackHA.class);
		gom.createMapObject(this.map, AttackableArea.AreaAttackArea.class, x,
				y, this);
	}

	public void destructor() {
		this.aaarea.destructor();
		super.destructor();
	}

	protected class AreaAttackArea extends Area {

		public AreaAttackArea(SRPGGameObjectManager gom, Sprite sp, int plnNo,
				int x, int y, StageMap m) {
			super(gom, sp, plnNo, x, y, m);
			this.createHorizon(AttackableArea.AreaAttackArea.AreaAttackHA.class);
			AttackableArea.this.aaarea = this;
		}

		protected class AreaAttackHA extends Area.HorizonArea {

			ChangeTracker ct = new ChangeTracker((Object) null);

			public AreaAttackHA(SRPGGameObjectManager gom, Sprite sp,
					int plnNo, int x, int hy, StageMap m) {
				super(gom, sp, plnNo, x, hy, m);
				AreaAttackArea.this.availables = this.map.current
						.getAreaAttackableArea(this.map.getCursored());
			}

			public void motion() {
				if (this.ct != null
						&& this.ct.isChanged(this.map.getCursored().clone())) {
					this.repaint = true;
					AreaAttackArea.this.availables = this.map.current
							.getAreaAttackableArea(this.map.getCursored());
				}

				super.motion();
			}
		}
	}

	protected class AttackHA extends Area.HorizonArea {

		public AttackHA(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
				int y, StageMap m) {
			super(gom, sp, plnNo, x, y, m);
		}
	}

	protected class AttackCursor extends Area.AreaCursor {

		UnitType[] target;

		public AttackCursor(SRPGGameObjectManager gom, Sprite sp, int plnNo,
				int x, int y, StageMap m) {
			super(gom, sp, plnNo, x, y, m);
			switch (AttackableArea.this.act) {
			case SUPPORT:
				this.color = new Color(2293759);
				break;
			case ALL:
				AttackableArea.this.act = ActionType.ATTACK;
			case ATTACK:
				this.color = new Color(16720418);
			}

			this.target = this.map.current.getType().attackable(
					AttackableArea.this.act);
		}

		protected void MCAction() {
			boolean existTarget = false;
			boolean[][] areaAttack = this.map.current
					.getAreaAttackableArea(this.map.getCursored());

			for (int x = 0; x < areaAttack.length; ++x) {
				for (int y = 0; y < areaAttack[x].length; ++y) {
					existTarget = existTarget
							|| areaAttack[x][y]
							&& this.map.unitMap[y][x] != null
							&& UnitType.contains(this.target,
									this.map.unitMap[y][x].getType());
				}
			}

			if (this.selected && existTarget
					&& AttackableArea.this.isAvailable(this.x, this.y)) {
				this.destructor();
				this.map.current.setAttack(this.x, this.y);
				this.select = true;
			} else if (this.canceled) {
				this.map.current.setCurrentAction((Action) null);
				this.destructor();
			}

		}

	}
}
