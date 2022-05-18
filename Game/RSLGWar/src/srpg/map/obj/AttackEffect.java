package srpg.map.obj;

import game.BlendMode;
import game.Sprite;
import java.util.Iterator;
import myutil.Coord;
import srpg.Damage;
import srpg.SRPGGameObjectManager;
import srpg.map.MapUserIF;
import srpg.map.StageDrawOrder;
import srpg.map.StageMap;
import srpg.map.UnitType;
import srpg.map.obj.Unit;

public abstract class AttackEffect extends MapUserIF {

	protected Coord current;
	protected Coord target;
	protected Coord targetCoord;

	public AttackEffect(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
			int y, StageMap m) {
		super(gom, sp, plnNo, x, y, m);
		sp.setPos(plnNo, StageDrawOrder.EFFECT.z());
		sp.setBlendMode(plnNo, BlendMode.DODGE);
		this.current = this.map.getHexCoord(this.map.current.getPos());
		this.setHexCenter(this.current);
		this.targetCoord = new Coord(Integer.valueOf(x), Integer.valueOf(y));
		this.target = this.map.getHexCoord((double) x, (double) y);
		this.setHexCenter(this.target);
	}

	protected void damage() {
		Unit current = this.map.current;
		UnitType[] var5;
		int var4 = (var5 = current.getType().attackable(
				current.getCurrentAction().actType)).length;

		for (int var3 = 0; var3 < var4; ++var3) {
			UnitType ut = var5[var3];
			Iterator var7 = this.map.searchUnits(
					current.getAreaAttackableArea(this.targetCoord), ut)
					.iterator();

			while (var7.hasNext()) {
				Unit unit = (Unit) var7.next();
				Damage d = current.getCurrentAction().damage;
				switch (current.getCurrentAction().actType) {
				case ATTACK:
				case ALL:
					unit.enemyDamage(d);
					break;
				case SUPPORT:
					unit.showDamage(d);
				}
			}
		}

	}

	protected void setHexCenter(Coord cood) {
		double var10001 = ((Double) cood.x).doubleValue();
		this.map.getClass();
		cood.x = Double.valueOf(var10001 + (double) (48.0F / 2.0F));
		cood.y = Double.valueOf(((Double) cood.y).doubleValue()
				+ (double) ((float) this.map.hexH / 2.0F));
	}

}
