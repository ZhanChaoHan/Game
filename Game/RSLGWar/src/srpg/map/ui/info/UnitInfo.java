package srpg.map.ui.info;

import game.Sprite;
import srpg.SRPGGameObjectManager;
import srpg.UnitStatus;
import srpg.map.StageMap;
import srpg.map.obj.Unit;
import srpg.map.ui.info.MapInformation;
import srpg.screen.Information;

public class UnitInfo extends MapInformation {

	protected int realHeight;
	protected Unit currentUnit;

	public UnitInfo(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
			int y, StageMap m) {
		super(gom, sp, plnNo, x, y, m);
		this.init(gom.width * 2 / 5, 100);
		this.realHeight = 80;
		this.title = "UnitInfo";
		sp.setPos(plnNo, 0.0D, (double) (gom.height - this.realHeight));
	}

	protected Information.StrData[] initSds() {
		return new Information.StrData[] {
				new Information.StrData("", this.width / 8,
						this.realHeight / 4 + 5),
				new Information.StrData("HP : ", this.width / 8,
						this.realHeight * 2 / 4 + 5),
				new Information.StrData("SP : ", this.width / 8,
						this.realHeight * 3 / 4 + 5),
				new Information.StrData("Str : ", this.width * 8 / 16 - 6,
						this.realHeight * 2 / 4 + 5),
				new Information.StrData("Agi : ", this.width * 8 / 16 - 6,
						this.realHeight * 3 / 4 + 5),
				new Information.StrData("Hit : ", this.width * 11 / 16,
						this.realHeight * 2 / 4 + 5),
				new Information.StrData("Flee : ", this.width * 11 / 16,
						this.realHeight * 3 / 4 + 5) };
	}

	protected void setData(Unit unit) {
		UnitStatus st = unit.getStatus();
		this.sds[0].str = this.sds[0].str + unit.getName();
		this.sds[1].str = this.sds[1].str + st.hp + " / " + st.maxHP;
		this.sds[2].str = this.sds[2].str + st.sp + " / " + st.maxSP;
		this.sds[3].str = this.sds[3].str + st.capa.strength;
		this.sds[4].str = this.sds[4].str + st.capa.agility;
		this.sds[5].str = this.sds[5].str + st.hit;
		this.sds[6].str = this.sds[6].str + st.flee;
	}

	public void mainMotion() {
		this.currentUnit = this.map.current;
		if (this.currentUnit == null) {
			this.sp.setView(this.plnNo, false);
		} else {
			this.sp.setView(this.plnNo, true);
			this.sds = this.initSds();
			this.setData(this.currentUnit);
		}
	}
}
