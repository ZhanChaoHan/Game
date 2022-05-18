package srpg.map.ui.ai;

import game.Sprite;
import java.util.Iterator;
import java.util.List;
import myutil.Coord;
import srpg.Action;
import srpg.ActionType;
import srpg.MapSearch;
import srpg.SRPGGameObjectManager;
import srpg.map.StageMap;
import srpg.map.UnitType;
import srpg.map.obj.Unit;
import srpg.map.ui.ai.EnemyAI;

public class EAIActive extends EnemyAI {

	protected EAIActive.StrategyMode smode;
	private final List acts;
	Unit nearest;
	Coord[] targetWay;
	Action bestAct;

	public EAIActive(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
			int y, StageMap m) {
		super(gom, sp, plnNo, x, y, m);
		this.acts = this.map.current.getStatus().usableActionList();
		EAIActive.StrategyMode.setActor(this.map.current);
		this.smode = EAIActive.StrategyMode.SUPPORT;
		if (!this.setTarget()) {
			this.smode = EAIActive.StrategyMode.ATTACK;
			this.setTarget();
		}

	}

	protected boolean setTarget() {
		MapSearch ms = this.map.current.initMS(new MapSearch(this.map.hex),
				true);
		this.nearest = null;
		this.targetWay = null;
		this.bestAct = null;
		List targets = this.smode.targetList(this.map);
		Unit currentTarget = null;

		while (!targets.isEmpty()
				&& (!this.smode.near() || this.targetWay == null)) {
			int dis = Integer.MAX_VALUE;
			int min = Integer.MAX_VALUE;
			Iterator var7 = targets.iterator();

			while (var7.hasNext()) {
				Unit act = (Unit) var7.next();
				dis = MapSearch.distanse(
						((Integer) this.map.current.getPos().x).intValue(),
						((Integer) this.map.current.getPos().y).intValue(),
						((Integer) act.getPos().x).intValue(),
						((Integer) act.getPos().y).intValue());
				if (min > dis) {
					min = dis;
					currentTarget = act;
				}
			}

			targets.remove(currentTarget);
			var7 = this.acts.iterator();

			while (var7.hasNext()) {
				Action act1 = (Action) var7.next();
				if (act1.actType == this.smode.actionType()
						&& (this.bestAct == null || this.smode.evalAct(act1,
								currentTarget) > this.smode.evalAct(
								this.bestAct, this.nearest))) {
					this.bestAct = act1;
					this.nearest = currentTarget;
					int lenMin = Integer.MAX_VALUE;
					Coord c = new Coord(Integer.valueOf(0), Integer.valueOf(0));
					boolean[][] aaarea = this.map.current.getAttackableArea(
							currentTarget.getPos(), this.bestAct, true);

					for (c.x = Integer.valueOf(0); ((Integer) c.x).intValue() < this.map.hex[0].length; c.x = Integer
							.valueOf(((Integer) c.x).intValue() + 1)) {
						for (c.y = Integer.valueOf(0); ((Integer) c.y)
								.intValue() < this.map.hex.length; c.y = Integer
								.valueOf(((Integer) c.y).intValue() + 1)) {
							if (aaarea[((Integer) c.x).intValue()][((Integer) c.y)
									.intValue()]) {
								ms.search(c, Integer.MAX_VALUE,
										this.map.current.getStatus().capa.jump);
								Coord[] way = ms.getWay(
										((Integer) c.x).intValue(),
										((Integer) c.y).intValue());
								if (way != null && lenMin > way.length) {
									lenMin = way.length;
									this.targetWay = way;
								}
							}
						}
					}
				}
			}
		}

		return this.targetWay != null && this.bestAct != null
				&& this.smode.evalAct(this.bestAct, this.nearest) > 0.0D;
	}

	protected boolean activation() {
		return true;
	}

	protected void moveStrategy() {
		if (this.smode.attackableTargets(this.map, this.bestAct).isEmpty()) {
			boolean[][] movable = this.map.current.getMovableArea(true);
			Coord farthestMove = null;
			Coord attackableMove = null;
			int maxA = Integer.MIN_VALUE;
			int dis = Integer.MIN_VALUE;
			int max = Integer.MIN_VALUE;
			this.map.current.setCurrentAction(this.bestAct);
			if (this.targetWay != null) {
				Coord[] var10 = this.targetWay;
				int var9 = this.targetWay.length;

				for (int var8 = 0; var8 < var9; ++var8) {
					Coord c = var10[var8];
					if (movable[((Integer) c.x).intValue()][((Integer) c.y)
							.intValue()]) {
						dis = MapSearch.distanse(((Integer) c.x).intValue(),
								((Integer) c.y).intValue(),
								((Integer) this.map.current.getPos().x)
										.intValue(),
								((Integer) this.map.current.getPos().y)
										.intValue());
						if (max < dis) {
							max = dis;
							farthestMove = c.clone();
						}

						if (maxA < dis
								&& this.map.current.getAttackableArea(c, true)[((Integer) this.nearest
										.getPos().x).intValue()][((Integer) this.nearest
										.getPos().y).intValue()]) {
							maxA = dis;
							attackableMove = c.clone();
						}
					}
				}
			}

			if (attackableMove != null) {
				this.map.current.setMove(attackableMove);
			} else {
				this.map.current.setMove(farthestMove);
			}

		}
	}

	protected void attackStrategy() {
		Action attack = null;
		Unit actTarget = null;
		double maxEffect = Double.MIN_VALUE;
		Iterator var6 = this.acts.iterator();

		while (var6.hasNext()) {
			Action act = (Action) var6.next();
			List atts = this.smode.attackableTargets(this.map, act);
			if (!atts.isEmpty()) {
				Unit target;
				if (atts.contains(this.nearest)) {
					target = this.nearest;
				} else {
					target = ((Unit[]) atts.toArray(new Unit[0]))[0];
				}

				double effect = this.smode.evalAct(act, target);
				if (maxEffect < effect) {
					attack = act;
					actTarget = target;
					maxEffect = effect;
				}
			}
		}

		if (attack != null) {
			this.map.current.setCurrentAction(attack);
			this.map.current.setAttack(
					((Integer) actTarget.getPos().x).intValue(),
					((Integer) actTarget.getPos().y).intValue());
		}
	}

	static enum StrategyMode {

		ATTACK("ATTACK", 0, (EAIActive.StrategyMode) null) {
			List targetList(StageMap map) {
				return EAIActive.searchFriends(map);
			}

			List attackableTargets(StageMap map, Action act) {
				return EAIActive.searchAttackableFriends(map, act);
			}

			UnitType targetType() {
				return UnitType.FRIEND;
			}

			ActionType actionType() {
				return ActionType.ATTACK;
			}

			double evalAct(Action act, Unit target) {
				return (double) (act.damage.hp + act.damage.sp);
			}

			boolean near() {
				return true;
			}
		},
		SUPPORT("SUPPORT", 1, (EAIActive.StrategyMode) null) {
			List targetList(StageMap map) {
				return EAIActive.searchDamagedEnemies(map, map.current
						.moveAndAttackableArea(ActionType.SUPPORT, false));
			}

			List attackableTargets(StageMap map, Action act) {
				return EAIActive.searchDamagedEnemies(map, map.current
						.getAttackableArea(map.current.getPos(), act, true));
			}

			UnitType targetType() {
				return UnitType.ENEMY;
			}

			ActionType actionType() {
				return ActionType.SUPPORT;
			}

			double evalAct(Action act, Unit target) {
				double score = 0.0D;
				if (target.getStatus().sp < target.getStatus().maxSP / 2) {
					score += (double) (-act.damage.sp)
							- (double) target.getStatus().sp / 4095.0D;
				}

				score = score < 0.0D ? 0.0D : score;
				if (target.getStatus().hp < target.getStatus().maxHP / 2) {
					score += (double) (-act.damage.hp * 255)
							- (double) target.getStatus().hp / 4095.0D;
				}

				return score;
			}

			boolean near() {
				return false;
			}
		};
		private static Unit actor;

		private StrategyMode(String var1, int var2) {
		}

		static void setActor(Unit u) {
			actor = u;
		}

		abstract List targetList(StageMap var1);

		abstract List attackableTargets(StageMap var1, Action var2);

		abstract UnitType targetType();

		abstract ActionType actionType();

		abstract double evalAct(Action var1, Unit var2);

		abstract boolean near();

		StrategyMode(String var1, int var2, EAIActive.StrategyMode var3) {
			this(var1, var2);
		}
	}
}
