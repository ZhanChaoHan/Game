package srpg;

import srpg.map.obj.AttackEffect;

public class Action {
	final String name;
	public final Range range;
	public final Range area;
	public final Damage damage;
	public final Damage cost;
	public final Class<? extends AttackEffect> effect;
	public final ActionType actType;

	Action(String name, Range range, Range area, Damage damage, ActionType act,
			Damage cost, Class<? extends AttackEffect> effect) {
		this.name = name;
		this.range = range;
		this.area = area;
		this.damage = damage;
		this.cost = cost;

		this.effect = effect;
		this.actType = act;
	}

	public boolean usable(UnitStatus s) {
		return (s.hp > this.cost.hp) && (s.sp >= this.cost.sp);
	}

	public void use(UnitStatus s) {
		s.damaging(this.cost);
	}

	public String toString() {
		return this.name;
	}
}