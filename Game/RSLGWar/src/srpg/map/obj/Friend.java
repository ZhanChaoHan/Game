// Decompiled by:       Fernflower v0.6
// Date:                24.10.2010 08:56:15
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package srpg.map.obj;

import game.Sprite;
import srpg.SRPGGameObjectManager;
import srpg.map.StageMap;
import srpg.map.UnitType;
import srpg.map.obj.Unit;
import srpg.map.ui.menu.CommandMenu;

public class Friend extends Unit {

	public Friend(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
			int y, StageMap m) {
		super(gom, sp, plnNo, x, y, m, UnitType.FRIEND);
		this.operator = CommandMenu.class;
	}
}
