package srpg;

import srpg.Action;
import srpg.ActionType;
import srpg.Damage;
import srpg.Range;
import srpg.UnitStatus;

public class Spell extends Action {

	public Spell(String name, Range range, Range area, Damage damage,
			ActionType act, Damage cost, Class effect) {
		super(name, range, area, damage, act, cost, effect);
	}

	public boolean usable(UnitStatus s) {
		return super.usable(s) && s.tension >= 1.0D;
	}

	public void use(UnitStatus s) {
		s.tension = 0.0D;
		super.use(s);
	}
}
