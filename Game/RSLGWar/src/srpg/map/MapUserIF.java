// Decompiled by:       Fernflower v0.6
// Date:                24.10.2010 08:56:14
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package srpg.map;

import game.Sprite;
import game.UserIF;
import srpg.SRPGGameObjectManager;
import srpg.map.StageMap;

public abstract class MapUserIF extends UserIF {

	protected final SRPGGameObjectManager gom;
	protected final StageMap map;

	public MapUserIF(SRPGGameObjectManager gom, Sprite sp, int plnNo, int x,
			int y, StageMap m) {
		super(gom, sp, plnNo, x, y);
		this.gom = gom;
		this.map = m;
	}
}
