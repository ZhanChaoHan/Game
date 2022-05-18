package srpg.map.obj;

import game.Sprite;
import myutil.Coord;
import srpg.SRPGGameObjectManager;
import srpg.map.StageMap;

public class MusoufuuinAE extends AttackEffect {
	Phase phase = Phase.CHARGE;
	Coord size;

	public MusoufuuinAE(SRPGGameObjectManager gom, Sprite sp, int plnNo,
			int xx, int yy, StageMap m) {
		super(gom, sp, plnNo, xx, yy, m);

		sp.setAnimeImg(plnNo, "animation_explosion010rot", 4);
		sp.setTrailCount(plnNo, 1);

		sp.setPos(plnNo, this.map.getHexCoord(this.map.current.getPos()));
		this.size = sp.getImgSize(plnNo);
		sp.setMov(plnNo, -((Integer) this.size.x).intValue() / 2 + 25,
				-((Integer) this.size.y).intValue() / 2);

		sp.playSE("eco00_r");
	}

	public void action() {
		switch (this.phase) {
		case CHARGE:
			if (!this.sp.isAnimeClosed(this.plnNo))
				break;
			this.phase = Phase.EXPLODE;

			this.sp.initImgs(this.plnNo);

			String file = "explosion1";
			this.sp.setAnimeImg(this.plnNo, file, 5);

			this.sp.setTrailCount(this.plnNo, 1);

			this.target = this.map.getHexCoord(this.x, this.y);

			this.sp.setPos(this.plnNo, this.target);
			this.sp.setMov(this.plnNo,
					-((Integer) this.size.x).intValue() / 2 - 80,
					-((Integer) this.size.y).intValue() / 2 - 85);

			this.sp.playSE("tm2r_bom31_b");

			break;
		case EXPLODE:
			this.target = this.map.getHexCoord(this.x, this.y);

			this.sp.setPos(this.plnNo, this.target);
			this.sp.setMov(this.plnNo,
					-((Integer) this.size.x).intValue() / 2 - 80,
					-((Integer) this.size.y).intValue() / 2 - 85);

			if (!this.sp.isAnimeClosed(this.plnNo))
				break;
			destructor();
			damage();
		}
	}

	private static enum Phase {
		CHARGE, EXPLODE;
	}
}