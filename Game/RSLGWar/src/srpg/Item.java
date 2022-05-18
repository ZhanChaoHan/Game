// Decompiled by:       Fernflower v0.6
// Date:                24.10.2010 08:56:14
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package srpg;

import srpg.Action;
import srpg.ActionType;
import srpg.Damage;
import srpg.Range;
import srpg.RangeType;
import srpg.UnitStatus;
import srpg.map.obj.CureAE;

public class Item extends Action {

	int count;

	Item(String name, Range range, Range area, Damage damage, ActionType act,
			Damage cost, Class effect, int count) {
		super(name, range, area, damage, act, cost, effect);
		this.count = count;
	}

	public boolean usable(UnitStatus s) {
		return this.count > 0 && super.usable(s);
	}

	public void use(UnitStatus s) {
		--this.count;
	}

	public String toString() {
		return this.name + "  x" + this.count;
	}

	static Item newCureHP(int count) {
		return new Item("cure HP", new Range(RangeType.ARCH, 0, 2, 0, 3),
				Range.SIMPLE, new Damage(-50, 0, 0, 1.0D), ActionType.SUPPORT,
				new Damage(0, 0, 0, 1.0D), CureAE.class, count);
	}

	static Item newCureSP(int count) {
		return new Item("cure SP", new Range(RangeType.ARCH, 0, 2, 0, 3),
				Range.SIMPLE, new Damage(0, -30, 0, 1.0D), ActionType.SUPPORT,
				new Damage(0, 0, 0, 1.0D), CureAE.class, count);
	}
}
