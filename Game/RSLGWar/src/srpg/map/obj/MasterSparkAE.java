package srpg.map.obj;

import game.Sprite;
import myutil.Coord;
import srpg.SRPGGameObjectManager;
import srpg.map.StageMap;

public class MasterSparkAE extends NormalAE {
	Phase phase = Phase.CHARGE;

	public MasterSparkAE(SRPGGameObjectManager gom, Sprite sp, int plnNo,
			int x, int y, StageMap m) {
		super(gom, sp, plnNo, x, y, m);

		sp.setAnimeImg(plnNo, "animation_mcf003_004a", 5);
		sp.setTrailCount(plnNo, 1);

		Coord size = sp.getImgSize(plnNo);
		sp.setMov(plnNo, -((Integer) size.x).intValue() / 2 - 28,
				-((Integer) size.y).intValue() / 2 - 28);

		sp.playSE("eco03");
	}

	public void action() {
		switch (this.phase) {
		case CHARGE:
			if (!this.sp.isAnimeClosed(this.plnNo))
				break;
			this.phase = Phase.SHOOT;

			this.sp.initImgs(this.plnNo);
			initBullet("tama5", 1, 33, "tm2_laser001");

			break;
		case SHOOT:
			super.action();
		}
	}

	private static enum Phase {
		CHARGE, SHOOT;
	}
}