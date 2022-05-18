package srpg.map.obj;

import game.Sprite;
import myutil.Coord;
import srpg.SRPGGameObjectManager;
import srpg.map.StageMap;

public class HomingAmuletAE extends NormalAE {
	Phase phase = Phase.BULLET;

	public HomingAmuletAE(SRPGGameObjectManager gom, Sprite sp, int plnNo,
			int x, int y, StageMap m) {
		super(gom, sp, plnNo, x, y, m);

		initBullet("tama4", 3, 15, "tm2r_gun18_d");
	}

	public void action() {
		switch (this.phase) {
		case BULLET:
			if (!bulletAction())
				break;
			this.phase = Phase.EXPLODE;

			this.sp.initImgs(this.plnNo);
			this.sp.setAnimeImg(this.plnNo, "animation_explosion001", 3);

			Coord size = this.sp.getImgSize(this.plnNo);
			this.sp.setPos(this.plnNo, this.target);
			this.sp.setMov(this.plnNo, -((Integer) size.x).intValue() / 2,
					-((Integer) size.y).intValue() / 2 - 20);

			this.sp.playSE("tm2_bom001");

			break;
		case EXPLODE:
			if (!this.sp.isAnimeClosed(this.plnNo))
				break;
			destructor();
			damage();
		}
	}

	private static enum Phase {
		BULLET, EXPLODE;
	}
}