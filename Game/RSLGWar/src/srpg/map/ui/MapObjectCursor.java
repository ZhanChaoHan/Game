// Decompiled by:       Fernflower v0.6
// Date:                24.10.2010 08:56:26
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package srpg.map.ui;

import game.Sprite;
import srpg.SRPGGameObjectManager;
import srpg.map.StageMap;
import srpg.screen.Cursor;

public abstract class MapObjectCursor extends Cursor {

	protected final SRPGGameObjectManager gom;
	protected final StageMap map;

	public MapObjectCursor(SRPGGameObjectManager gom, Sprite sp, int plnNo,
			int x, int y, StageMap m) {
		super(gom, sp, plnNo, x, y);
		this.gom = gom;
		this.map = m;
	}
}
