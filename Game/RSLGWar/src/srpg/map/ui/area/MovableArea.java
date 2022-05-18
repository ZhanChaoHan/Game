package srpg.map.ui.area;

import game.Sprite;
import srpg.SRPGGameObjectManager;
import srpg.map.StageMap;
import srpg.map.ui.area.Area;

public class MovableArea extends Area {

	public MovableArea(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
			int y, StageMap m) {
		super(gom, sp, plnNo, x, y, m);
		this.availables = this.map.current.getMovableArea();
		gom.createMapObject(this.map, MovableArea.MoveCursor.class, x, y, this);
		this.createHorizon(MovableArea.MoveHA.class);
	}

	protected class MoveCursor extends Area.AreaCursor {

		public MoveCursor(SRPGGameObjectManager gom, Sprite sp, int plnNo,
				int x, int y, StageMap m) {
			super(gom, sp, plnNo, x, y, m);
		}

		protected void MCAction() {
			if (this.selected
					&& (this.map.unitMap[this.y][this.x] == null || this.map.unitMap[this.y][this.x] == this.map.current)
					&& MovableArea.this.isAvailable(this.x, this.y)) {
				this.destructor();
				this.map.current.setMove(this.x, this.y);
				this.select = true;
			} else if (this.canceled) {
				this.destructor();
			}

		}
	}

	protected class MoveHA extends Area.HorizonArea {

		public MoveHA(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
				int y, StageMap m) {
			super(gom, sp, plnNo, x, y, m);
		}
	}
}
