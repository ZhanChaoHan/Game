package srpg.map.ui.area;

import game.ChangeTracker;
import game.Sprite;
import srpg.SRPGGameObjectManager;
import srpg.map.StageMap;
import srpg.map.obj.Unit;
import srpg.map.ui.area.Area;

public class MoveAttackableArea extends Area {

	public MoveAttackableArea(SRPGGameObjectManager gom, Sprite sp, int plnNo,
			int x, int y, StageMap m) {
		super(gom, sp, plnNo, x, y, m);
		this.createHorizon(MoveAttackableArea.MoveAttackHA.class);
		this.availables = this.map.newBoolArea();
	}

	public void setUnit(Unit unit) {
		if (unit == null) {
			this.availables = this.map.newBoolArea();
		} else {
			this.availables = unit.moveAndAttackableArea();
		}

	}

	protected class MoveAttackHA extends Area.HorizonArea {

		ChangeTracker ct = new ChangeTracker((Object) null);

		public MoveAttackHA(SRPGGameObjectManager gom, Sprite sp, int plnNo,
				int x, int hy, StageMap m) {
			super(gom, sp, plnNo, x, hy, m);
		}

		public void motion() {
			super.motion();
			if (this.ct != null) {
				this.repaint = this.ct
						.isChanged(MoveAttackableArea.this.availables);
			}

		}
	}
}
