// Decompiled by:       Fernflower v0.6
// Date:                24.10.2010 08:56:14
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package srpg.map;

import myutil.Coord;
import srpg.map.StageMap;

public enum Direction {

	UPRIGHT("UPRIGHT", 0, 1, -1, (Direction) null) {
		public boolean isBehind(Coord current, Coord next) {
			this.setXY(current, next);
			return LEFT.equals(this.x, this.y)
					|| DOWNLEFT.equals(this.x, this.y)
					|| DOWNRIGHT.equals(this.x, this.y);
		}
	},
	RIGHT("RIGHT", 1, 1, 0, (Direction) null) {
		public boolean isBehind(Coord current, Coord next) {
			this.setXY(current, next);
			return UPLEFT.equals(this.x, this.y) || LEFT.equals(this.x, this.y)
					|| DOWNLEFT.equals(this.x, this.y);
		}
	},
	DOWNRIGHT("DOWNRIGHT", 2, 0, 1, (Direction) null) {
		public boolean isBehind(Coord current, Coord next) {
			this.setXY(current, next);
			return UPRIGHT.equals(this.x, this.y)
					|| UPLEFT.equals(this.x, this.y)
					|| LEFT.equals(this.x, this.y);
		}
	},
	DOWNLEFT("DOWNLEFT", 3, -1, 1, (Direction) null) {
		public boolean isBehind(Coord current, Coord next) {
			this.setXY(current, next);
			return UPLEFT.equals(this.x, this.y)
					|| UPRIGHT.equals(this.x, this.y)
					|| RIGHT.equals(this.x, this.y);
		}
	},
	LEFT("LEFT", 4, -1, 0, (Direction) null) {
		public boolean isBehind(Coord current, Coord next) {
			this.setXY(current, next);
			return UPRIGHT.equals(this.x, this.y)
					|| RIGHT.equals(this.x, this.y)
					|| DOWNRIGHT.equals(this.x, this.y);
		}
	},
	UPLEFT("UPLEFT", 5, 0, -1, (Direction) null) {
		public boolean isBehind(Coord current, Coord next) {
			this.setXY(current, next);
			return RIGHT.equals(this.x, this.y)
					|| DOWNRIGHT.equals(this.x, this.y)
					|| DOWNLEFT.equals(this.x, this.y);
		}
	};
	int difX;
	int difY;
	protected int x;
	protected int y;

	private Direction(String var1, int var2, int x, int y) {
		this.difX = x;
		this.difY = y;
	}

	public boolean isFront(Coord current, Coord next) {
		this.setXY(current, next);
		return this.equals(this.x, this.y);
	}

	public abstract boolean isBehind(Coord var1, Coord var2);

	boolean equals(int x, int y) {
		return this.difX == x && this.difY == y;
	}

	protected void setXY(Coord current, Coord next) {
		this.x = ((Integer) StageMap.toVectorCoord(next).x).intValue()
				- ((Integer) StageMap.toVectorCoord(current).x).intValue();
		this.y = ((Integer) next.y).intValue()
				- ((Integer) current.y).intValue();
	}

	Direction(String var1, int var2, int var3, int var4, Direction var5) {
		this(var1, var2, var3, var4);
	}
}
