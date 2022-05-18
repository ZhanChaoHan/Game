// Decompiled by:       Fernflower v0.6
// Date:                24.10.2010 08:56:27
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package srpg.map.ui;

import game.Sprite;
import srpg.SRPGGameObjectManager;
import srpg.map.StageMap;
import srpg.map.obj.Unit;
import srpg.map.ui.MapCursor;
import srpg.map.ui.area.MoveAttackableArea;

public class SelectCursor extends MapCursor {

	boolean first = true;
	boolean actioning = false;
	MoveAttackableArea maa = null;

	public SelectCursor(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
			int y, StageMap m) {
		super(gom, sp, plnNo, x, y, m);
	}

	public void destructor() {
		if (this.maa != null) {
			this.maa.destructor();
		}

		super.destructor();
	}

	protected void MCAction() {
		if (!this.first && this.maa == null) {
			this.maa = (MoveAttackableArea) this.map.createMapObject(
					MoveAttackableArea.class, 0, 0);
			this.maa.setUnit((Unit) null);
		}

		this.actioning = true;
		if (!this.first && !this.canceled) {
			if (this.selected) {
				this.maa.setUnit(this.map.unitMap[this.y][this.x]);
				this.select = true;
			}

		} else {
			this.first = false;
			this.actioning = false;
			if (this.maa != null) {
				this.maa.setUnit((Unit) null);
			}

			this.gom.createMapObject(this.map, this.map.current.operator, 0, 0);
		}
	}

	public void motion() {
		if (!this.actioning) {
			this.x = ((Integer) this.map.current.getPos().x).intValue();
			this.y = ((Integer) this.map.current.getPos().y).intValue();
		}

		super.motion();
	}

	public int processPriority() {
		return 0;
	}
}
