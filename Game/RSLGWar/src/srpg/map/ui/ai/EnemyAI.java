package srpg.map.ui.ai;

import game.Draw;
import game.Plane;
import game.Sprite;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import srpg.Action;
import srpg.SRPGGameObjectManager;
import srpg.map.MapUserIF;
import srpg.map.StageMap;
import srpg.map.UnitType;
import srpg.map.obj.Unit;

public abstract class EnemyAI extends MapUserIF {

	protected Random rnd = new Random();
	protected EnemyAI.Mode mode;

	public EnemyAI(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
			int y, StageMap m) {
		super(gom, sp, plnNo, x, y, m);
		this.mode = EnemyAI.Mode.STANDBY;
		sp.setDraw(plnNo, new Draw() {
			public void drawing(Graphics g, Plane pln) {
			}
		});
	}

	public void action() {
		switch (this.mode) {
		case MOVE:
			this.moveStrategy();
			this.mode = EnemyAI.Mode.ATTACK;
			break;
		case ATTACK:
			this.destructor();
			this.attackStrategy();
			break;
		case STANDBY:
			if (this.activation()) {
				this.mode = EnemyAI.Mode.MOVE;
			} else {
				this.destructor();
			}
		}

	}

	protected static List searchFriends(StageMap map) {
		boolean[][] area = map.newBoolArea();
		boolean[][] var5 = area;
		int var4 = area.length;

		for (int var3 = 0; var3 < var4; ++var3) {
			boolean[] flags = var5[var3];
			Arrays.fill(flags, true);
		}

		return map.searchUnits(area, UnitType.FRIEND);
	}

	protected static List searchAttackableFriends(StageMap map, Action act) {
		return map.searchUnits(
				map.current.getAttackableArea(map.current.getPos(), act, true),
				UnitType.FRIEND);
	}

	protected List searchMoveAttackableFriends() {
		return this.map.searchUnits(this.map.current.moveAndAttackableArea(),
				UnitType.FRIEND);
	}

	protected static List searchDamagedEnemies(StageMap map, boolean[][] area) {
		ArrayList enemys = map.searchUnits(area, UnitType.ENEMY);
		Iterator i = enemys.iterator();

		while (i.hasNext()) {
			Unit e = (Unit) i.next();
			if (!isDamaged(e)) {
				i.remove();
			}
		}

		return enemys;
	}

	protected static boolean isDamaged(Unit unit) {
		return unit.getStatus().hp < unit.getStatus().maxHP / 2
				|| unit.getStatus().sp < unit.getStatus().maxSP / 2;
	}

	protected abstract boolean activation();

	protected abstract void moveStrategy();

	protected abstract void attackStrategy();

	public void destructor() {
		super.destructor();
		this.map.current.setActionable(false);
	}

	protected static enum Mode {
		MOVE, ATTACK, STANDBY;
	}
}
