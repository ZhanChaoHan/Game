// Decompiled by:       Fernflower v0.6
// Date:                24.10.2010 08:56:16
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package srpg.map.obj;

import game.Sprite;
import srpg.SRPGGameObjectManager;
import srpg.map.StageMap;
import srpg.map.obj.NormalAE;

public class IllusionLaserAE extends NormalAE {

	public IllusionLaserAE(SRPGGameObjectManager gom, Sprite sp, int plnNo,
			int x, int y, StageMap m) {
		super(gom, sp, plnNo, x, y, m);
		this.initBullet("tama4", 9, 22, "tm2_laser000");
	}

	public void action() {
		super.action();
	}
}
