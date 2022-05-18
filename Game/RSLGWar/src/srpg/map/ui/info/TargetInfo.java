package srpg.map.ui.info;

import game.Sprite;
import srpg.SRPGGameObjectManager;
import srpg.map.StageMap;
import srpg.map.ui.info.UnitInfo;

public class TargetInfo extends UnitInfo {

	public TargetInfo(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
			int y, StageMap m) {
		super(gom, sp, plnNo, x, y, m);
		sp.setPos(plnNo, (double) (gom.width - this.width),
				(double) (gom.height - this.realHeight));
	}

	public void mainMotion() {
		this.currentUnit = this.map.unitMap[((Integer) this.current.y)
				.intValue()][((Integer) this.current.x).intValue()];
		if (this.currentUnit != null && this.currentUnit != this.map.current) {
			this.sp.setView(this.plnNo, true);
			this.sds = this.initSds();
			this.setData(this.currentUnit);
		} else {
			this.sp.setView(this.plnNo, false);
		}
	}
}
