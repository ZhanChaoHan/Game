package srpg.map;

import game.ZOrder;

public enum MapDrawOrder implements ZOrder {
	MAP, AREA, MAP_CURSOR, UNIT;

	public double z() {
		return StageDrawOrder.MAP_OBJECT.z() + ordinal() / 65535.0D;
	}
}