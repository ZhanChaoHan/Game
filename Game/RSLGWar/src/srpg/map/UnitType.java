package srpg.map;

import srpg.ActionType;
import srpg.map.Direction;

public enum UnitType {

	OBSTACLE("OBSTACLE", 0, (UnitType) null) {

		public UnitType[] attackable(ActionType act) {
			switch (act) {
			case ATTACK:
				return new UnitType[0];
			case SUPPORT:
				return new UnitType[0];
			default:
				return UnitType.values();
			}
		}
	},

	FRIEND("FRIEND", 1, (UnitType) null) {
		public UnitType[] attackable(ActionType act) {
			switch (act) {
			case ATTACK:
				return new UnitType[] { ENEMY };
			case SUPPORT:
				return new UnitType[] { FRIEND };
			default:
				return UnitType.values();
			}
		}

	},

	ENEMY("ENEMY", 2, (UnitType) null) {

		public UnitType[] attackable(ActionType act) {
			switch (act) {
			case ATTACK:
				return new UnitType[] { FRIEND };
			case SUPPORT:
				return new UnitType[] { ENEMY };
			default:
				return UnitType.values();
			}
		}

	};

	private Direction dir;

	private UnitType(String var1, int var2) {
	}

	public void setInitDir(Direction dir) {
		this.dir = dir;
	}

	public Direction getInitDir() {
		return this.dir;
	}

	public abstract UnitType[] attackable(ActionType var1);

	public boolean isAttackable(UnitType target, ActionType act) {
		return contains(this.attackable(act), target);
	}

	public static boolean contains(UnitType[] uts, UnitType ut) {
		UnitType[] var5 = uts;
		int var4 = uts.length;

		for (int var3 = 0; var3 < var4; ++var3) {
			UnitType u = var5[var3];
			if (ut == u) {
				return true;
			}
		}

		return false;
	}

	UnitType(String var1, int var2, UnitType var3) {
		this(var1, var2);
	}
}
