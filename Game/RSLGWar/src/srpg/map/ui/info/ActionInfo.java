package srpg.map.ui.info;

import game.Sprite;
import java.awt.Color;
import myutil.Coord;
import srpg.Action;
import srpg.ActionType;
import srpg.Damage;
import srpg.SRPGGameObjectManager;
import srpg.map.StageMap;
import srpg.map.UnitType;
import srpg.map.obj.Unit;
import srpg.map.ui.info.MapInformation;
import srpg.screen.Information;

public class ActionInfo extends MapInformation {

	int realHeight;

	public ActionInfo(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
			int y, StageMap m) {
		super(gom, sp, plnNo, x, y, m);
		this.init(gom.width * 1 / 5, 100);
		this.realHeight = 80;
		this.title = "       \uff1e\uff1e\uff1e\uff1e";
		sp.setPos(plnNo, (double) (gom.width * 2 / 5),
				(double) (gom.height - this.realHeight));
	}

	private Information.StrData[] initSds() {
		int xOff = this.width * 3 / 16 + 2;
		int scale = this.width * 2 / 16;
		return new Information.StrData[] {
				new Information.StrData("HIT:", xOff, this.realHeight / 4 + 5),
				new Information.StrData("HP :", xOff,
						this.realHeight * 2 / 4 + 5),
				new Information.StrData("SP :", xOff,
						this.realHeight * 3 / 4 + 5),
				new Information.StrData("", xOff + scale * 5 / 2,
						this.realHeight / 4 + 5),
				new Information.StrData("", xOff + scale * 3 / 2,
						this.realHeight * 2 / 4 + 5),
				new Information.StrData("", xOff + scale * 3 / 2,
						this.realHeight * 3 / 4 + 5),
				new Information.StrData("|", xOff + scale * 3,
						this.realHeight * 2 / 4 + 5),
				new Information.StrData("|", xOff + scale * 3,
						this.realHeight * 3 / 4 + 5),
				new Information.StrData("", xOff + scale * 7 / 2,
						this.realHeight * 2 / 4 + 5),
				new Information.StrData("", xOff + scale * 7 / 2,
						this.realHeight * 3 / 4 + 5) };
	}

	public void mainMotion() {
		if (this.map.current != null) {
			Action act = this.map.current.getCurrentAction();
			if (act != null && this.map.current.getType() == UnitType.FRIEND) {
				this.sp.setView(this.plnNo, true);
				this.sds = this.initSds();
				this.setColorData(this.sds[4], act.cost.hp);
				this.setColorData(this.sds[5], act.cost.sp);
				Unit currentUnit = this.map.unitMap[((Integer) this.current.y)
						.intValue()][((Integer) this.current.x).intValue()];
				if (currentUnit != null
						&& this.map.current.getType().isAttackable(
								currentUnit.getType(), act.actType)) {
					Damage d = null;
					switch (act.actType) {
					case ATTACK:
						d = currentUnit.calcDamage(act.damage);
						break;
					case SUPPORT:
						d = act.damage;
						break;
					case ALL:
						d = currentUnit.calcDamage(act.damage);
					}

					this.sds[3].str = this.sds[3].str
							+ (int) (d.hit < 0.0D ? 0.0D
									: (d.hit < 1.0D ? d.hit * 100.0D : 100.0D))
							+ " %";
					this.setColorData(this.sds[8], d.hp);
					this.setColorData(this.sds[9], d.sp);
				}
				this.current = new Coord(Integer.valueOf(act.hashCode()),
						Integer.valueOf(0));
			} else {
				this.sp.setView(this.plnNo, false);
			}
		}
	}

	private void setColorData(Information.StrData sd, int data) {
		if (data > 0) {
			sd.c = new Color(13382434);
			sd.str = sd.str + "-";
		} else if (data < 0) {
			sd.c = new Color(0xaa33);
			sd.str = sd.str + "+";
		} else {
			sd.c = Color.BLACK;
			sd.str = sd.str + "\u00b1";
		}

		sd.str = sd.str + Math.abs(data);
	}

}
