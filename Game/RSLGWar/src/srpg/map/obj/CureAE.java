// Decompiled by:       Fernflower v0.6
// Date:                24.10.2010 08:56:15
// Copyright:           2008-2009, Stiver
// Home page:           http://www.reversed-java.com

package srpg.map.obj;

import game.Sprite;
import srpg.SRPGGameObjectManager;
import srpg.map.StageMap;
import srpg.map.obj.AttackEffect;

public class CureAE extends AttackEffect {

	public CureAE(SRPGGameObjectManager gom, Sprite sp, int plnNo, int xx,
			int yy, StageMap m) {
		super(gom, sp, plnNo, xx, yy, m);
		String file = "animation_heal";
		sp.setAnimeImg(plnNo, file, 3);
		sp.setTrailCount(plnNo, 2);
		sp.setPos(plnNo, this.map.getHexCoord((double) xx, (double) yy));
		this.map.getClass();
		sp.setMov(plnNo, (double) (-48 - 7), (double) (-this.map.hexH * 2));
		sp.playSE("tm2_power000");
	}

	public void action() {
		this.sp.setPos(this.plnNo,
				this.map.getHexCoord((double) this.x, (double) this.y));
		Sprite var10000 = this.sp;
		int var10001 = this.plnNo;
		this.map.getClass();
		var10000.setMov(var10001, (double) (-48 - 7),
				(double) (-this.map.hexH * 2));
		if (this.sp.isAnimeClosed(this.plnNo)) {
			this.destructor();
			this.damage();
		}

	}
}
